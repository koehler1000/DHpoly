package de.dhpoly.feld.control;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.model.Ressource;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.spieler.Spieler;

public class Ressourcenfeld implements Feld
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
	public void betreteFeld(Spieler spieler, int augensumme, Wetter aktuellesWetter)
	{
		// TODO Auto-generated method stub

	}

	public Ressource getRessource()
	{
		return ressource;
	}
}
