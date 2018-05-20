package de.dhpoly.nachricht.model;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.empfaenger.model.Empfaenger;
import de.dhpoly.nachricht.view.NachrichtUI;
import de.dhpoly.oberflaeche.view.Oberflaeche;

public class Nachricht extends Datenobjekt
{
	private static final long serialVersionUID = 1L;

	private String nachrichtentext = "Keine Nachricht angegeben";
	private Empfaenger empfaenger = Empfaenger.ALLE;

	public Nachricht(String nachrichtentext, Empfaenger empfaenger)
	{
		this.nachrichtentext = nachrichtentext;
		this.empfaenger = empfaenger;
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

	public Empfaenger getEmpfaenger()
	{
		return empfaenger;
	}

	@Override
	public Class<? extends Oberflaeche> getClassUI()
	{
		return NachrichtUI.class;
	}
}
