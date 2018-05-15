package de.dhpoly.feld.control;

import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spielfeld.model.RessourcenfeldDaten;

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

	public FeldRessource(RessourcenfeldDaten aktuellesFeld, Einstellungen einstellungen)
	{
		super("Ressource");
		this.einstellungen = einstellungen;
		this.ressource = aktuellesFeld.getRessource();
	}

	@Override
	protected void spielerBetrittFeld(Spieler spieler, int augensumme, Spiel spiel)
	{
		spieler.einzahlen(new RessourcenDatensatz(ressource, einstellungen.getRessourcenErtrag(),
				ressource.getRessourcenFeldString()));
	}

	public Ressource getRessource()
	{
		return ressource;
	}

	@Override
	public boolean gehoertSpieler(Spieler spieler)
	{
		return false;
	}
}
