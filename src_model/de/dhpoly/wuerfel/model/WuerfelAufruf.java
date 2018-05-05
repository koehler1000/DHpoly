package de.dhpoly.wuerfel.model;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.wuerfel.view.WuerfelAufrufUI;

public class WuerfelAufruf extends Datenobjekt
{
	private static final long serialVersionUID = 1L;
	private Spieler spieler;

	public WuerfelAufruf(Spieler spieler)
	{
		this.spieler = spieler;
	}

	@Override
	public String getTitel()
	{
		return "Würfelaufruf";
	}

	public Spieler getSpielerDaten()
	{
		return spieler;
	}

	@Override
	public Class<? extends Oberflaeche> getClassUI()
	{
		return WuerfelAufrufUI.class;
	}
}
