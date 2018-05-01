package de.dhpoly.spieler.model;

import de.dhpoly.datenobjekt.Datenobjekt;

public class SpielerDaten extends Datenobjekt
{
	private static final long serialVersionUID = 1L;

	private SpielerTyp typ;
	private String name;

	public SpielerDaten(SpielerTyp typ, String name)
	{
		super();
		this.typ = typ;
		this.name = name;
	}

	public SpielerTyp getTyp()
	{
		return typ;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public String getTitel()
	{
		return "Spielerdaten";
	}
}
