package de.dhpoly.kartenstapel.view;

import de.dhpoly.karte.control.BezahlKarte;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.Oberflaeche;

public class KartenOberflaeche
{
	public KartenOberflaeche(BezahlKarte karte)
	{
		Oberflaeche.getInstance().zeigeAufRand("Ereigniskarte",
				ElementFactory.getTextInfoPanel("Ereigniskarte", karte.getBeschreibung()));
	}
}