package de.dhpoly.oberflaeche;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.bilderverwalter.view.Bild;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.view.SpielerFarben;

public class ElementFactory
{
	private static final String ARIAL = "arial";

	private static final Color FARBE_DESIGN = new Color(225, 225, 225);

	private static final Color FARBE_KONTRAST = new Color(100, 100, 100);

	private static final Font SCHRIFT_NORMAL = new Font(ARIAL, Font.PLAIN, 20);

	private static final Font SCHRIFT_UEBERSCHRIFT = new Font(ARIAL, Font.BOLD, 30);

	private static Bilderverwalter bilderverwalter = new Bilderverwalter();

	private ElementFactory()
	{}
	
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

	public static JTabbedPane getTabbedPane()
	{
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(FARBE_DESIGN);
		tabbedPane.setFont(SCHRIFT_UEBERSCHRIFT);
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

	private static void setzeRand(JPanel pnl, int dicke, Color farbe)
	{
		pnl.setBorder(BorderFactory.createLineBorder(farbe, dicke));
	}

	/**
	 * Spieler ist optional -> falls null > Kontrastfarbe
	 **/
	public static void setzeRand(JPanel pnl, int dicke, Spieler spieler)
	{
		Optional<Spieler> sp = Optional.ofNullable(spieler);
		Color farbe = sp.isPresent() ? SpielerFarben.getSpielerfarbe(spieler.getSpielerNr()) : FARBE_KONTRAST;
		setzeRand(pnl, dicke, farbe);
	}

	public static JButton getSpielerButton(Spieler spieler)
	{
		JButton butSpieler = getButton(spieler.getName());
		Color farbe = Optional.ofNullable(spieler).isPresent() ? SpielerFarben.getSpielerfarbe(spieler.getSpielerNr())
				: FARBE_KONTRAST;
		butSpieler.setBorder(new LineBorder(FARBE_KONTRAST));
		butSpieler.setBackground(farbe);
		butSpieler.setForeground(FARBE_KONTRAST);
		return butSpieler;
	}

	public static Component erzeugeScrollPanel(Component panel)
	{
		JScrollPane pane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setBackground(FARBE_KONTRAST);

		JPanel pnl = erzeugePanel();
		pnl.setLayout(new BorderLayout());
		pnl.add(pane, BorderLayout.CENTER);

		JButton butHoch = getButtonUeberschrift("^");
		butHoch.addActionListener(getScrollListener(pane, -100));
		pnl.add(butHoch, BorderLayout.NORTH);

		JButton butRunter = getButtonUeberschrift("v");
		butRunter.addActionListener(getScrollListener(pane, +100));
		pnl.add(butRunter, BorderLayout.SOUTH);

		pane.getViewport().setViewPosition(new Point(0, 0));
		return pnl;
	}

	private static ActionListener getScrollListener(JScrollPane pane, int verschiebung)
	{
		return e -> {
			int position = pane.getViewport().getViewPosition().y;

			position += verschiebung;

			if (position < 0)
			{
				position = 0;
			}

			pane.getViewport().setViewPosition(new Point(0, position));
		};
	}

	public static JTable getTable(TableModel dataModel)
	{
		JTable table = new JTable(dataModel);
		table.setFont(SCHRIFT_NORMAL);
		table.setBackground(FARBE_DESIGN);
		table.setRowHeight(SCHRIFT_NORMAL.getSize() + 20);
		return table;
	}
}
