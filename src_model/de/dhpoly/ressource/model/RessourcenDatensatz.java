package de.dhpoly.ressource.model;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.oberflaeche.view.Oberflaeche;

public class RessourcenDatensatz extends Datenobjekt
{
	private static final long serialVersionUID = 1L;

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

	@Override
	public String getTitel()
	{
		return "Datensatz";
	}

	@Override
	public Class<? extends Oberflaeche> getClassUI()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
