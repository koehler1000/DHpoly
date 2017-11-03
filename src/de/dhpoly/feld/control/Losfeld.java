package de.dhpoly.feld.control;

import de.dhpoly.feld.Feld;
import de.dhpoly.spiel.model.Balancing;
import de.dhpoly.spieler.Spieler;

public class Losfeld implements Feld
{

	@Override
	public String getBeschriftung()
	{
		return "Los";
	}

	@Override
	public void betreteFeld(Spieler spieler, int augensumme)
	{
		spieler.einzahlen(Balancing.UEBER_LOS);
	}

}
