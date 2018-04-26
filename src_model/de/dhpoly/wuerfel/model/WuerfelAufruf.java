package de.dhpoly.wuerfel.model;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.datenobjekt.spieler.model.SpielerDaten;

public class WuerfelAufruf extends Datenobjekt
{
	private static final long serialVersionUID = 1L;
	private SpielerDaten spieler;

	public WuerfelAufruf(SpielerDaten spieler)
	{
		this.spieler = spieler;
	}

	@Override
	public String getTitel()
	{
		return "W�rfelaufruf";
	}

	public SpielerDaten getSpielerDaten()
	{
		return spieler;
	}
}
