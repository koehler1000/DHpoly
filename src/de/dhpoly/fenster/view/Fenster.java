package de.dhpoly.fenster.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.ressource.model.Ressource;

public class Fenster extends JFrame
{
	private static final Color FARBE_DESIGN = new Color(225, 225, 225);

	private static final Color FARBE_KONTRAST = new Color(100, 100, 100);

	private static final Font SCHRIFT_NORMAL = new Font("arial", Font.PLAIN, 20);

	private static final Font SCHRIFT_UEBERSCHRIFT = new Font("arial", Font.BOLD, 30);

	private static final Font SCHRIFT_SPIELFELD_STRASSENNAME = new Font("arial", Font.BOLD, 15);

	private static final Font SCHRIFT_SPIELFELD_BESITZER = new Font("arial", Font.PLAIN, 12);

	private static final long serialVersionUID = 1L;

	private static Bilderverwalter bilderverwalter = new Bilderverwalter();

	private JPanel inhalt;

	public Fenster(JPanel pnlInhalt)
	{
		this.setTitle("");
		this.setIconImage(bilderverwalter.getBild(Bilderverwalter.LOGO).getImage());
		this.setLayout(new BorderLayout());
		setInhalt(pnlInhalt);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		this.setSize(500, 500);
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

	public Fenster()
	{
		this(new JPanel());
		this.setVisible(false);
	}

	public void setInhalt(JPanel pnl, String string)
	{
		setInhalt(pnl);
		this.setTitle(string);
		this.setVisible(true);
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

	public void schliessen()
	{
		this.setVisible(false);
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
		JButton butButton = new JButton(text);
		butButton.setBorder(new LineBorder(getKontrastfarbe(), 10));
		butButton.setFont(getStandardFont());
		butButton.setBackground(getKontrastfarbe());
		butButton.setForeground(getDesignfarbe());
		return butButton;
	}

	public static JButton getButtonUeberschrift(String text)
	{
		JButton butButton = getButton(text);
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

	public static void setWuerfelBildPfad(Bild bild, int wuerfelErgebnis)
	{
		bild.setBildPfad(bilderverwalter.getWuerfelPfad(wuerfelErgebnis));
	}

	public static Bilderverwalter getBilderverwalter()
	{
		return bilderverwalter;
	}
}
