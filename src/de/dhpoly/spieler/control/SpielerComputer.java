package de.dhpoly.spieler.control;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.karte.Karte;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spiel.Spiel;

public class SpielerComputer extends SpielerImpl
{
	public SpielerComputer(String name, Spiel spiel)
	{
		super(name, spiel);
	}

	@Override
	public void zeigeTransaktionsvorschlag(Transaktion transaktion)
	{
		// TODO AI entscheiden lassen
	}

	@Override
	public void zeigeKaufmoeglichkeit(Strasse strasse)
	{
		// TODO AI entscheiden lassen
	}

	@Override
	public void zeigeKarte(Karte karte)
	{
		spiel.verarbeiteKarte(karte);
	}

	@Override
	public void zeigeHausbauMoeglichkeit()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void zeigeDatenobjekt(Datenobjekt objekt)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void leereRand()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setSpielfeldAnsicht(SpielfeldAnsicht ansicht)
	{
		// TODO Auto-generated method stub

	}
}
