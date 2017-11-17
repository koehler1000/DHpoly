package de.dhpoly.feld.control;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.feld.Feld;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.spieler.Spieler;

public class Losfeld extends FeldImpl
{
	private Einstellungen einstellungen;

	public Losfeld(Einstellungen einstellungenImpl)
	{
		super("Los");
		this.einstellungen = einstellungenImpl;
	}

	@Override
	public String getBeschriftung()
	{
		return "Los";
	}

	@Override
	protected void spielerBetrittFeld(Spieler spieler, int augensumme, Wetter aktuellesWetter)
	{
		spieler.einzahlen(einstellungen.getBetragBetretenLos());
	}
}
