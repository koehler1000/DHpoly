package de.dhpoly.karte.model;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.karte.view.KarteUI;
import de.dhpoly.oberflaeche.view.Oberflaeche;

public class RueckenKarte extends Datenobjekt implements Karte
{
	private static final long serialVersionUID = 1L;
	private int felder = 0;

	public RueckenKarte(int felder)
	{
		this.felder = felder;
	}

	@Override
	public String getBeschreibung()
	{
		return "Rücken Sie " + felder + " Felder";
	}

	@Override
	public String getTitel()
	{
		return "Sie werden verschoben...";
	}

	public int getFelder()
	{
		return felder;
	}

	@Override
	public Class<? extends Oberflaeche> getClassUI()
	{
		return KarteUI.class;
	}
}
