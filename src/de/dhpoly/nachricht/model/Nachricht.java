package de.dhpoly.nachricht.model;

import de.dhpoly.datenobjekt.Datenobjekt;

public class Nachricht extends Datenobjekt
{
	private static final long serialVersionUID = 1L;

	private String nachrichtentext = "Keine Nachricht angegeben";

	public Nachricht(String nachrichtentext)
	{
		this.nachrichtentext = nachrichtentext;
	}

	public String getNachricht()
	{
		return nachrichtentext;
	}

	@Override
	public String getTitel()
	{
		return "Nachricht";
	}
}
