package de.dhpoly.spiel.model;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.spieler.model.Spieler;

public class SpielStart extends Datenobjekt
{
	private static final long serialVersionUID = 1L;

	private Spieler spieler;

	public SpielStart(Spieler spieler)
	{
		this.spieler = spieler;
	}

	@Override
	public String getTitel()
	{
		return "Spielstart";
	}

	@Override
	public Class<? extends Oberflaeche> getClassUI()
	{
		return null;
	}

	public Spieler getSpieler()
	{
		return spieler;
	}
}
