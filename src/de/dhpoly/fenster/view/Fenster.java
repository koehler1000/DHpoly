package de.dhpoly.fenster.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.oberflaeche.Oberflaeche;
import de.dhpoly.ressource.model.Ressource;

public class Fenster extends JFrame // NOSONAR
{
	private static final String ARIAL = "arial";

	private static final Color FARBE_DESIGN = new Color(225, 225, 225);

	private static final Color FARBE_KONTRAST = new Color(100, 100, 100);

	private static final Font SCHRIFT_NORMAL = new Font(ARIAL, Font.PLAIN, 20);

	private static final Font SCHRIFT_UEBERSCHRIFT = new Font(ARIAL, Font.BOLD, 30);

	private static final Font SCHRIFT_SPIELFELD_STRASSENNAME = new Font(ARIAL, Font.BOLD, 15);

	private static final Font SCHRIFT_SPIELFELD_BESITZER = new Font(ARIAL, Font.PLAIN, 12);

	private static final long serialVersionUID = 1L;

	private static Bilderverwalter bilderverwalter = new Bilderverwalter();

	private JPanel inhalt;

	public Fenster(JPanel pnlInhalt)
	{
		this.setTitle("");
		this.setIconImage(bilderverwalter.getBild(Bilderverwalter.LOGO).getImage());
		this.setLayout(new BorderLayout());
		setInhalt(pnlInhalt);

		this.setSize(500, 750);
		this.setVisible(true);
	}

	public Fenster(JPanel pnlInhalt, boolean hauptfenster)
	{
		this(pnlInhalt);
		if (hauptfenster)
		{
			this.setTitle("DHPoly");
			this.setIconImage(bilderverwalter.getBild(Bilderverwalter.LOGO).getImage());
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}

	public Fenster(JPanel pnlInhalt, String string)
	{
		this(pnlInhalt);
		this.setTitle(string);
	}

	private void setInhalt(JPanel pnl)
	{
		if (inhalt != null)
		{
			this.remove(inhalt);
		}
		inhalt = pnl;
		this.add(pnl);
	}

	public static JTextArea getTextFeldUeberschrift(String text)
	{
		JTextArea txtArea = getTextFeld(text, false);
		txtArea.setFont(getUeberschriftFont());
		return txtArea;
	}

	public static JTextArea getTextFeld(String text, boolean aenderbar)
	{
		JTextArea txtArea = new JTextArea(text);
		txtArea.setEditable(aenderbar);
		txtArea.setFont(getStandardFont());
		txtArea.setBackground(getDesignfarbe());
		txtArea.setBorder(new LineBorder(getDesignfarbe(), 10));
		txtArea.setLineWrap(true);
		txtArea.setForeground(getKontrastfarbe());
		return txtArea;
	}

	public static JButton getButton(String text)
	{
		JButton butButton = new JButton(getButtonText(text));
		butButton.setBorder(new LineBorder(getKontrastfarbe(), 10));
		butButton.setFont(getStandardFont());
		butButton.setBackground(getKontrastfarbe());
		butButton.setForeground(getDesignfarbe());
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
		butButton.setFont(getUeberschriftFont());
		return butButton;
	}

	public static Font getStandardFont()
	{
		return SCHRIFT_NORMAL;
	}

	public static Font getUeberschriftFont()
	{
		return SCHRIFT_UEBERSCHRIFT;
	}

	public static Font getSpielfeldStrassennameFont()
	{
		return SCHRIFT_SPIELFELD_STRASSENNAME;
	}

	public static Font getSpielfeldBesitzerFont()
	{
		return SCHRIFT_SPIELFELD_BESITZER;
	}

	public static Color getDesignfarbe()
	{
		return FARBE_DESIGN;
	}

	public static Color getKontrastfarbe()
	{
		return FARBE_KONTRAST;
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

	public static void zeigeInfo(String titel, String string)
	{
		JPanel pnlInhalt = new JPanel(new BorderLayout(10, 10));
		pnlInhalt.setBorder(new LineBorder(getDesignfarbe(), 10));
		pnlInhalt.setBackground(getDesignfarbe());
		JButton butUeberschrift = getButtonUeberschrift(titel);
		JButton butText = getButton(string);

		pnlInhalt.add(butUeberschrift, BorderLayout.NORTH);
		pnlInhalt.add(butText);

		Oberflaeche.getInstance().zeigeAufRand("Info", pnlInhalt);

		butUeberschrift.addActionListener(e -> pnlInhalt.setVisible(false));
		butText.addActionListener(e -> pnlInhalt.setVisible(false));
	}
}
