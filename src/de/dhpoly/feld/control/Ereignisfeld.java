package de.dhpoly.feld.control;

import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.karte.control.BezahlKarte;
import de.dhpoly.spieler.Spieler;

public class Ereignisfeld implements Feld
{
	private List<BezahlKarte> kartenstapel;

	public Ereignisfeld(List<BezahlKarte> kartenstapel)
	{
		this.kartenstapel = kartenstapel;
	}

	@Override
	public String getBeschriftung()
	{
		return "Ereignisfeld";
	}

	@Override
	public void betreteFeld(Spieler spieler, int augensumme)
	{
		// TODO Auto-generated method stub

	}

}
