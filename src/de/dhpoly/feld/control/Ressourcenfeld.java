package de.dhpoly.feld.control;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.Spieler;

public class Ressourcenfeld extends FeldImpl
{
	private Ressource ressource;
	private Einstellungen einstellungen;

	public Ressourcenfeld(Ressource ressourcentyp)
	{
		super("Ressource");
		this.ressource = ressourcentyp;
	}

	@Override
	protected void spielerBetrittFeld(Spieler spieler, int augensumme, Wetter aktuellesWetter)
	{
		// TODO Anzeige der erfolgreichen Buchung erstellen
		spieler.einzahlen(new RessourcenDatensatz(ressource, einstellungen.getRessourcenErtrag()));
	}

	public Ressource getRessource()
	{
		return ressource;
	}
}
