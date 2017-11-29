package de.dhpoly.ressource.control;

import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.model.Ressource;

public class RessourcenDatensatzImpl implements RessourcenDatensatz
{
	private Ressource ressource;
	private int anzahl;
	private String beschreibung;

	public RessourcenDatensatzImpl(Ressource ressource, int anzahl)
	{
		this.ressource = ressource;
		this.anzahl = anzahl;
	}

	public RessourcenDatensatzImpl(Ressource ressource, int anzahl, String beschreibung)
	{
		this(ressource, anzahl);
		this.beschreibung = beschreibung;
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

	@Override
	public String getBeschreibung()
	{
		return beschreibung;
	}
}
