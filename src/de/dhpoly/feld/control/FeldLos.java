package de.dhpoly.feld.control;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.Spieler;

public class FeldLos extends FeldImpl
{
	private Einstellungen einstellungen;

	public FeldLos(Einstellungen einstellungenImpl)
	{
		super("Los");
		this.einstellungen = einstellungenImpl;
	}

	@Override
	protected void spielerBetrittFeld(Spieler spieler, int augensumme, Wetter aktuellesWetter)
	{
		spieler.einzahlen(new RessourcenDatensatz(Ressource.GELD, einstellungen.getBetragBetretenLos(), "Los"));
	}

	@Override
	public void laufeUeberFeld(Spieler spieler)
	{
		super.laufeUeberFeld(spieler);
		spieler.einzahlen(new RessourcenDatensatz(Ressource.GELD, einstellungen.getBetragPassierenLos(), "Los"));
	}
}
