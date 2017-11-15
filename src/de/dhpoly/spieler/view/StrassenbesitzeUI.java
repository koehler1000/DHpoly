package de.dhpoly.spieler.view;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.spieler.Spieler;
import observerpattern.Beobachter;

public class StrassenbesitzeUI extends JPanel implements Beobachter
{
	private static final long serialVersionUID = 1L;

	private Felderverwaltung felderverwaltung;
	private Spieler spieler;

	public StrassenbesitzeUI(Felderverwaltung felderverwaltung, Spieler spieler)
	{
		this.spieler = spieler;
		this.felderverwaltung = felderverwaltung;

		spieler.addBeobachterHinzu(this);
		informiere();
	}

	@Override
	public void informiere()
	{
		List<Feld> felderDesSpielers = felderverwaltung.getFelder(spieler);

		this.setLayout(new GridLayout(1, felderDesSpielers.size()));

		for (Feld feld : felderDesSpielers)
		{
			this.add(new JLabel(feld.getBeschriftung()));
		}
	}
}
