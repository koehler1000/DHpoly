package de.dhpoly.feld.model;

import de.dhpoly.ressource.model.Ressource;

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
}
