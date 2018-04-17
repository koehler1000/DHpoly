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
		strasse.kaufe(this);
	}

	@Override
	public void verarbeiteKarte(Karte karte)
	{
		spiel.verarbeiteKarte(karte);
	}

	@Override
	public void zeigeHausbauMoeglichkeit()
	{
		// ignorieren
	}

	@Override
	public void zeigeDatenobjekt(Datenobjekt objekt)
	{
		// ignorieren
	}

	@Override
	public void leereRand()
	{
		// ignorieren
	}

	@Override
	public void setSpielfeldAnsicht(SpielfeldAnsicht ansicht)
	{
		// ignorieren
	}

	@Override
	public void setWuerfelnMoeglich(boolean value)
	{
		if (value)
		{
			spiel.wuerfeln(this);
		}
	}

	@Override
	public void setWuerfelWeitergabeMoeglich(boolean value)
	{
		if (value)
		{
			spiel.wuerfelWeitergeben(this);
		}
	}
}
