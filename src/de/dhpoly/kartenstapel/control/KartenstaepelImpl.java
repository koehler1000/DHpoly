package de.dhpoly.kartenstapel.control;

import java.util.List;

import de.dhpoly.karte.control.BezahlKarte;
import de.dhpoly.kartenstapel.Kartenstaepel;

public class KartenstaepelImpl implements Kartenstaepel
{
	private Kartenstapel ereigniskarten;
	private Kartenstapel gemeinschaftskarten;

	public KartenstaepelImpl(List<BezahlKarte> ereigniskarten, List<BezahlKarte> gemeinschaftskarten)
	{
		this.ereigniskarten = new Kartenstapel(ereigniskarten);
		this.gemeinschaftskarten = new Kartenstapel(gemeinschaftskarten);
	}

	@Override
	public BezahlKarte zieheEreigniskarte()
	{
		return ereigniskarten.ziehen();
	}

	@Override
	public BezahlKarte zieheGemeinschaftskarte()
	{
		return gemeinschaftskarten.ziehen();
	}
}
