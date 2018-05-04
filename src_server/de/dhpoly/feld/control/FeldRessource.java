package de.dhpoly.feld.control;

import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.model.SpielerDaten;

public class FeldRessource extends FeldImpl
{
	private Ressource ressource;
	private Einstellungen einstellungen;

	public FeldRessource(Ressource ressourcentyp, Einstellungen einstellungen)
	{
		super("Ressource");
		this.einstellungen = einstellungen;
		this.ressource = ressourcentyp;
	}

	@Override
	protected void spielerBetrittFeld(Spieler spieler, int augensumme, Wetter aktuellesWetter)
	{
		spieler.getDaten().einzahlen(new RessourcenDatensatz(ressource, einstellungen.getRessourcenErtrag(),
				ressource.getRessourcenFeldString()));
	}

	public Ressource getRessource()
	{
		return ressource;
	}

	@Override
	public boolean gehoertSpieler(SpielerDaten spielerDaten)
	{
		return false;
	}

	@Override
	public boolean gehoertSpieler(Spieler spieler)
	{
		return false;
	}
}
