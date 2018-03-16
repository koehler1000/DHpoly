package de.dhpoly.kartenstapel.view;

import de.dhpoly.karte.control.BezahlKarte;
import de.dhpoly.oberflaeche.Oberflaeche;

public class KartenOberflaeche
{
	public KartenOberflaeche(BezahlKarte karte)
	{
		Oberflaeche.getInstance().zeigeNachricht(karte.getBeschreibung());
	}
}