package de.dhpoly.karte.control;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.karte.Karte;
import de.dhpoly.karte.model.Wetter;

public class WetterKarte extends Datenobjekt implements Karte
{
	private static final long serialVersionUID = 1L;

	private Wetter wetter;

	public WetterKarte(Wetter wetter)
	{
		this.wetter = wetter;
	}

	@Override
	public String getBeschreibung()
	{
		return wetter.getBeschreibung();
	}

	@Override
	public String getTitel()
	{
		return "Wetterumschwung";
	}

	public Wetter getWetter()
	{
		return wetter;
	}

}
