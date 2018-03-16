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
}
