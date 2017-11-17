package de.dhpoly.ressource.control;

import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.model.Ressource;

public class RessourcenDatensatzImpl implements RessourcenDatensatz
{
	private Ressource ressource;
	private int anzahl;

	public RessourcenDatensatzImpl(Ressource ressource, int anzahl)
	{
		this.ressource = ressource;
		this.anzahl = anzahl;
	}

	@Override
	public Ressource getRessource()
	{
		return ressource;
	}

	@Override
	public int getAnzahl()
	{
		return anzahl;
	}

	@Override
	public String getString()
	{
		return ressource.getString(anzahl);
	}
}
