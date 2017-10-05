package de.dhpoly.kartenstapel.control;

import java.util.List;

import de.dhpoly.kartenstapel.Kartenstaepel;
import de.dhpoly.kartenstapel.model.Karte;

public class KartenstaepelImpl implements Kartenstaepel
{
	private Kartenstapel ereigniskarten;
	private Kartenstapel gemeinschaftskarten;

	public KartenstaepelImpl(List<Karte> ereigniskarten, List<Karte> gemeinschaftskarten)
	{
		this.ereigniskarten = new Kartenstapel(ereigniskarten);
		this.gemeinschaftskarten = new Kartenstapel(gemeinschaftskarten);
	}

	@Override
	public Karte zieheEreigniskarte()
	{
		return ereigniskarten.ziehen();
	}

	@Override
	public Karte zieheGemeinschaftskarte()
	{
		return gemeinschaftskarten.ziehen();
	}
}
