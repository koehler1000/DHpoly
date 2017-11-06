package de.dhpoly.einstellungen.control;

import de.dhpoly.einstellungen.Einstellungen;

public class EinstellungenImpl implements Einstellungen
{
	int betragBetretenLos = 2000;

	@Override
	public int getBetragBetretenLos()
	{
		return betragBetretenLos;
	}

	@Override
	public void setBetragBetretenLos(int betragBetretenLos)
	{
		this.betragBetretenLos = betragBetretenLos;
	}
}
