package de.dhpoly.feld.control;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.ressource.control.RessourcenDatensatzImpl;
import de.dhpoly.ressource.model.Ressource;
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
	protected void spielerBetrittFeld(Spieler spieler, int augensumme, Wetter aktuellesWetter)
	{
		spieler.einzahlen(new RessourcenDatensatzImpl(Ressource.GELD, einstellungen.getBetragBetretenLos(), "Los"));
	}
}
