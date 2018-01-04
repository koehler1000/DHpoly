package de.dhpoly.kartenstapel.view;

import de.dhpoly.fenster.view.Fenster;
import de.dhpoly.karte.control.BezahlKarte;

public class KartenOberflaeche
{
	public KartenOberflaeche(BezahlKarte karte)
	{
		Fenster.zeigeInfo("Ereigniskarte", karte.getBeschreibung());
	}
}