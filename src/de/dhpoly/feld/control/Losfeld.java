package de.dhpoly.feld.control;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.feld.Feld;
import de.dhpoly.spieler.Spieler;

public class Losfeld implements Feld
{
	private Einstellungen einstellungen;

	public Losfeld(Einstellungen einstellungenImpl)
	{
		this.einstellungen = einstellungenImpl;
	}

	@Override
	public String getBeschriftung()
	{
		return "Los";
	}

	@Override
	public void betreteFeld(Spieler spieler, int augensumme)
	{
		spieler.einzahlen(einstellungen.getBetragBetretenLos());
	}

}
