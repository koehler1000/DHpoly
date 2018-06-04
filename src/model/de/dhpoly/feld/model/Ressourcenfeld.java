package de.dhpoly.feld.model;

import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.model.Spieler;

public class Ressourcenfeld extends FeldDaten
{
	private static final long serialVersionUID = 1L;

	private Ressource ressource;

	public Ressourcenfeld(Ressource ressource)
	{
		super(FeldTyp.RESSOURCE);
		this.ressource = ressource;
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
