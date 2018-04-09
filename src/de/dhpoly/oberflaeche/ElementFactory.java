package de.dhpoly.oberflaeche;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.bilderverwalter.view.Bild;
import de.dhpoly.ressource.model.Ressource;

public class ElementFactory
{
	private ElementFactory()
	{}

	private static final String ARIAL = "arial";

	private static final Color FARBE_DESIGN = new Color(225, 225, 225);

	private static final Color FARBE_KONTRAST = new Color(100, 100, 100);

	private static final Font SCHRIFT_NORMAL = new Font(ARIAL, Font.PLAIN, 20);

	private static final Font SCHRIFT_UEBERSCHRIFT = new Font(ARIAL, Font.BOLD, 30);

	private static Bilderverwalter bilderverwalter = new Bilderverwalter();

	public static JTextArea getTextFeldUeberschrift(String text)
	{
		JTextArea txtArea = getTextFeld(text, false);
		txtArea.setFont(SCHRIFT_UEBERSCHRIFT);
		return txtArea;
	}

	public static JTextArea getTextFeld(String text, boolean aenderbar)
	{
		JTextArea txtArea = new JTextArea(text);
		txtArea.setEditable(aenderbar);
		txtArea.setFont(SCHRIFT_NORMAL);
		txtArea.setBackground(FARBE_DESIGN);
		txtArea.setBorder(new LineBorder(FARBE_DESIGN, 10));
		txtArea.setLineWrap(true);
		txtArea.setForeground(FARBE_KONTRAST);
		return txtArea;
	}

	public static JButton getButton(String text)
	{
		JButton butButton = new JButton(getButtonText(text));
		butButton.setBorder(new LineBorder(FARBE_KONTRAST, 10));
		butButton.setFont(SCHRIFT_NORMAL);
		butButton.setBackground(FARBE_KONTRAST);
		butButton.setForeground(FARBE_DESIGN);
		return butButton;
	}

	private static String getButtonText(String text)
	{
		return "<html><body style='text-align:center'>" + text.replaceAll(System.lineSeparator(), "<br>")
				+ "</body></html>";
	}

	public static JButton getButtonUeberschrift(String text)
	{
		JButton butButton = getButton(getButtonText(text));
		butButton.setFont(SCHRIFT_UEBERSCHRIFT);
		return butButton;
	}

	public static JButton getButtonUeberschrift(String beschriftung, Color backcolor)
	{
		JButton but = getButtonUeberschrift(beschriftung);
		but.setBackground(backcolor);
		but.setBorder(new LineBorder(backcolor, 10));
		return but;
	}

	public static Component getBild(String pfad, Color hintergrundfarbe)
	{
		Component bild = getBild(pfad);
		bild.setBackground(hintergrundfarbe);
		return bild;
	}

	public static Bild getBild(String pfad)
	{
		return new Bild(pfad, bilderverwalter);
	}

	public static Bild getBild(Ressource ressource)
	{
		return getBild(Bilderverwalter.getPfad(ressource));
	}

	public static Bild getBild(int i)
	{
		return getBild(bilderverwalter.getWuerfelPfad(i));
	}

	public static JButton getButtonBild(String pfad)
	{
		JButton butBild = getButton("");
		butBild.setText(getHTMLBild(pfad));
		return butBild;
	}

	private static String getHTMLBild(String pfad)
	{
		char anfuehrungszeichen = '"';
		return "<html><img src=" + // html
				anfuehrungszeichen + pfad + anfuehrungszeichen + // src
				" alt = " + anfuehrungszeichen + pfad + anfuehrungszeichen + // alternative Anzeige
				" width=" + anfuehrungszeichen + "50px" + anfuehrungszeichen + " /></html>";
	}

	public static void setWuerfelBildPfad(Bild bild, int wuerfelErgebnis)
	{
		bild.setBildPfad(bilderverwalter.getWuerfelPfad(wuerfelErgebnis));
	}

	public static Bilderverwalter getBilderverwalter()
	{
		return bilderverwalter;
	}

	public static Component getNachrichtPanel(String titel, String string)
	{
		JPanel pnlInhalt = new JPanel(new BorderLayout(10, 10));
		pnlInhalt.setBorder(new LineBorder(FARBE_DESIGN, 10));
		pnlInhalt.setBackground(FARBE_DESIGN);
		JButton butUeberschrift = getButtonUeberschrift(titel);
		JButton butText = getButton(string);

		pnlInhalt.add(butUeberschrift, BorderLayout.NORTH);
		pnlInhalt.add(butText);

		butUeberschrift.addActionListener(e -> pnlInhalt.setVisible(false));
		butText.addActionListener(e -> pnlInhalt.setVisible(false));

		return pnlInhalt;
	}

	public static JTabbedPane getTabbedPane()
	{
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(FARBE_DESIGN);
		return tabbedPane;
	}

	public static void bearbeitePanel(JPanel pnl)
	{
		pnl.setBackground(FARBE_DESIGN);
		pnl.setLayout(new BorderLayout(10, 10));
		pnl.setBorder(new LineBorder(FARBE_DESIGN, 10));
	}

	public static JPanel erzeugePanel()
	{
		JPanel pnl = new JPanel();
		bearbeitePanel(pnl);
		return pnl;
	}
}
