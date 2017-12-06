package de.dhpoly.feld.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.dhpoly.feld.control.Strasse;
import de.dhpoly.fenster.view.Fenster;
import de.dhpoly.spieler.Spieler;

public class StrasseKaufenUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	private Fenster fenster;

	public StrasseKaufenUI(Strasse strasse, Spieler spieler)
	{
		this.setLayout(new GridLayout(1, 2));

		this.add(new StrasseInfoUI(strasse));

		JButton butKaufen = new JButton("Kaufen");
		butKaufen.addActionListener(e -> kaufen(strasse, spieler));

		JButton butAbbrechen = new JButton("Abbrechen");
		butAbbrechen.addActionListener(e -> abbrechen());

		JPanel pnlOptionen = new JPanel(new GridLayout(2, 1, 10, 10));
		pnlOptionen.add(butKaufen);
		pnlOptionen.add(butAbbrechen);

		this.add(pnlOptionen);

		this.setSize(1000, 1000);
		this.setVisible(true);

		fenster = new Fenster(this);
	}

	private void kaufen(Strasse strasse, Spieler spieler)
	{
		strasse.kaufe(spieler);
		fenster.schliessen();
	}

	private void abbrechen()
	{
		fenster.schliessen();
	}
}
