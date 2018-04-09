package de.dhpoly.feld.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.dhpoly.feld.control.Strasse;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.spieler.Spieler;

public class StrasseKaufenUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	public StrasseKaufenUI(Strasse strasse, Spieler spieler)
	{
		ElementFactory.bearbeitePanel(this);
		this.setLayout(new GridLayout(1, 2, 10, 10));

		this.add(new StrasseInfoUI(strasse));

		JButton butKaufen = ElementFactory.getButtonUeberschrift("Kaufen");
		butKaufen.addActionListener(e -> kaufen(strasse, spieler));

		JButton butAbbrechen = ElementFactory.getButtonUeberschrift("Abbrechen");
		butAbbrechen.addActionListener(e -> abbrechen());

		JPanel pnlOptionen = ElementFactory.erzeugePanel();
		pnlOptionen.setLayout(new GridLayout(2, 1, 10, 10));
		pnlOptionen.add(butKaufen);
		pnlOptionen.add(butAbbrechen);

		this.add(pnlOptionen);
	}

	private void kaufen(Strasse strasse, Spieler spieler)
	{
		strasse.kaufe(spieler);
		spieler.loescheRand();
		// FIXME
		// Oberflaeche.getInstance().leereRand();
	}

	private void abbrechen()
	{
		// FIXME
		// Oberflaeche.getInstance().leereRand();
	}
}
