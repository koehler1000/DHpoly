package de.dhpoly.nachricht.model;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.empfaenger.model.Empfaenger;
import de.dhpoly.nachricht.view.NachrichtUI;
import de.dhpoly.oberflaeche.view.Oberflaeche;

public class Nachricht extends Datenobjekt
{
	private static final long serialVersionUID = 1L;

	private String text = "Keine Nachricht angegeben";
	private String titel = "Nachricht";
	private Empfaenger empfaenger = Empfaenger.ALLE;

	public Nachricht(String text, Empfaenger empfaenger)
	{
		this.text = text;
		this.empfaenger = empfaenger;
	}

	public Nachricht(String text, Empfaenger empfaenger, String titel)
	{
		this(text, empfaenger);
		this.titel = titel;
	}

	public String getText()
	{
		return text;
	}

	@Override
	public String getTitel()
	{
		return titel;
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
