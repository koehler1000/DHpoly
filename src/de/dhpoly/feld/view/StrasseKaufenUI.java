package de.dhpoly.feld.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.control.Strasse;
import de.dhpoly.fenster.view.Fenster;
import de.dhpoly.spieler.Spieler;

public class StrasseKaufenUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	private Fenster fenster = new Fenster();

	public StrasseKaufenUI(Strasse strasse, Spieler spieler)
	{
		this.setLayout(new GridLayout(1, 2, 10, 10));
		this.setBackground(Fenster.getDesignfarbe());

		this.add(new StrasseInfoUI(strasse, fenster));

		JButton butKaufen = Fenster.getButtonUeberschrift("Kaufen");
		butKaufen.addActionListener(e -> kaufen(strasse, spieler));

		JButton butAbbrechen = Fenster.getButtonUeberschrift("Abbrechen");
		butAbbrechen.addActionListener(e -> abbrechen());

		JPanel pnlOptionen = new JPanel(new GridLayout(2, 1, 10, 10));
		pnlOptionen.setBorder(new LineBorder(Fenster.getDesignfarbe(), 10));
		pnlOptionen.setBackground(Fenster.getDesignfarbe());
		pnlOptionen.add(butKaufen);
		pnlOptionen.add(butAbbrechen);

		this.add(pnlOptionen);

		this.setSize(1000, 1000);
		this.setVisible(true);

		fenster.setInhalt(this, "Straﬂe kaufen");
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
