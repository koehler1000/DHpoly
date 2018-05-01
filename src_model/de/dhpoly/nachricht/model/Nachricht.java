package de.dhpoly.nachricht.model;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.nachricht.view.NachrichtUI;
import de.dhpoly.oberflaeche.view.Oberflaeche;

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

	@Override
	public Class<? extends Oberflaeche> getClassUI()
	{
		return NachrichtUI.class;
	}
}
