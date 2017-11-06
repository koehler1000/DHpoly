package de.dhpoly.karte.control;

import de.dhpoly.feld.Feld;
import de.dhpoly.karte.Karte;

public class RueckenKarte implements Karte
{
	private Feld zielFeld;

	public RueckenKarte(Feld zielFeld)
	{
		this.zielFeld = zielFeld;
	}

	@Override
	public String getBeschreibung()
	{
		return "Rücken Sie auf das Feld: " + System.lineSeparator() + zielFeld.getBeschriftung();
	}

	@Override
	public String getTitel()
	{
		return "Sie werden verschoben...";
	}

	public Feld getZiel()
	{
		return zielFeld;
	}

}
