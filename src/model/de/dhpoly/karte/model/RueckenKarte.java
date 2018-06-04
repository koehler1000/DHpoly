package de.dhpoly.karte.model;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.Feld;
import de.dhpoly.karte.view.KarteUI;
import de.dhpoly.oberflaeche.view.Oberflaeche;

public class RueckenKarte extends Datenobjekt implements Karte
{
	private static final long serialVersionUID = 1L;
	private transient Feld zielFeld;

	public RueckenKarte(Feld zielFeld)
	{
		this.zielFeld = zielFeld;
	}

	@Override
	public String getBeschreibung()
	{
		return "R�cken Sie auf das Feld: " + System.lineSeparator() + zielFeld.getBeschriftung();
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

	@Override
	public Class<? extends Oberflaeche> getClassUI()
	{
		return KarteUI.class;
	}

}
