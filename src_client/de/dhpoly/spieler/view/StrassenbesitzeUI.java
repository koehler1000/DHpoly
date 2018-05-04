package de.dhpoly.spieler.view;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spieler.Spieler;
import observerpattern.Beobachter;

public class StrassenbesitzeUI extends Oberflaeche implements Beobachter // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private transient Felderverwaltung felderverwaltung;
	private transient Spieler spieler;

	public StrassenbesitzeUI(Felderverwaltung felderverwaltung, Spieler spieler, SpielfeldAnsicht ansicht)
	{
		super(ansicht);
		this.spieler = spieler;
		this.felderverwaltung = felderverwaltung;

		update();
	}

	@Override
	public void update()
	{
		List<Feld> felderDesSpielers = felderverwaltung.getFelder(spieler);

		this.setLayout(new GridLayout(1, felderDesSpielers.size()));

		for (Feld feld : felderDesSpielers)
		{
			this.add(new JLabel(feld.getBeschriftung()));
		}
	}

	@Override
	public boolean isEinmalig()
	{
		return false;
	}
}
