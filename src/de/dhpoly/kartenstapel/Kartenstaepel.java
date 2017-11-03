package de.dhpoly.kartenstapel;

import de.dhpoly.karte.control.BezahlKarte;

public interface Kartenstaepel
{
	public BezahlKarte zieheEreigniskarte();

	public BezahlKarte zieheGemeinschaftskarte();
}
