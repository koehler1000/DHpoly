package de.dhpoly.spielfeld.model;

import de.dhpoly.feld.model.FeldDaten;
import de.dhpoly.feld.model.FeldTyp;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.model.Spieler;

public class RessourcenfeldDaten extends FeldDaten
{
	private static final long serialVersionUID = 1L;

	private Ressource ressource;

	public RessourcenfeldDaten(Ressource ressource)
	{
		super(FeldTyp.RESSOURCE);
		this.ressource = ressource;
	}

	public Ressource getRessource()
	{
		return ressource;
	}

	@Override
	public boolean gehoertSpieler(Spieler spieler)
	{
		return false;
	}

}
