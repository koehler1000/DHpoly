package de.dhpoly.ressource;

import de.dhpoly.ressource.model.Ressource;

public interface RessourcenDatensatz
{
	public Ressource getRessource();

	public int getAnzahl();

	public String getString();

	public String getBeschreibung();
}
