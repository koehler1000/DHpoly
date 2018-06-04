package de.dhpoly.wuerfel.model;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.spieler.model.Spieler;

public class WuerfelWeitergabe extends Datenobjekt
{
	private static final long serialVersionUID = 1L;

	private Spieler spieler;

	@Override
	public String getTitel()
	{
		return "Würfelweitergabe";
	}

	@Override
	public Class<? extends Oberflaeche> getClassUI()
	{
		return null;
	}

	public WuerfelWeitergabe(Spieler spieler)
	{
		this.spieler = spieler;
	}

	public Spieler getSpieler()
	{
		return spieler;
	}
}
