package de.dhpoly.feld.model;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.view.FeldUI;
import de.dhpoly.oberflaeche.view.Oberflaeche;

public class FeldDaten extends Datenobjekt
{
	private static final long serialVersionUID = 1L;

	@Override
	public String getTitel()
	{
		return "Feld";
	}

	@Override
	public Class<? extends Oberflaeche> getClassUI()
	{
		return FeldUI.class;
	}
}
