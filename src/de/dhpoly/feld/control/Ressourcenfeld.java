package de.dhpoly.feld.control;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.model.Ressource;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.spieler.Spieler;

public class Ressourcenfeld extends Feld
{
	private Ressource ressource;

	public Ressourcenfeld(Ressource ressourcentyp)
	{
		this.ressource = ressourcentyp;
	}

	@Override
	public String getBeschriftung()
	{
		return "Ressource";
	}

	@Override
	protected void spielerBetrittFeld(Spieler spieler, int augensumme, Wetter aktuellesWetter)
	{
		// TODO Was passiert, wenn Ressourcenfelder betreten werden?
	}

	public Ressource getRessource()
	{
		return ressource;
	}
}
