package de.dhpoly.ressource.model;

public class RessourcenDatensatz
{
	private Ressource ressource;
	private int anzahl;
	private String beschreibung = "<keine Beschreibung angegeben>";

	public RessourcenDatensatz(Ressource ressource, int anzahl)
	{
		this.ressource = ressource;
		this.anzahl = anzahl;
	}

	public RessourcenDatensatz(Ressource ressource, int anzahl, String beschreibung)
	{
		this(ressource, anzahl);
		this.beschreibung = beschreibung;
	}

	public Ressource getRessource()
	{
		return ressource;
	}

	public int getAnzahl()
	{
		return anzahl;
	}

	public String getString()
	{
		return ressource.getString(anzahl);
	}

	public String getBeschreibung()
	{
		return beschreibung;
	}
}
