package de.dhpoly.kartenstapel.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.dhpoly.karte.Karte;
import de.dhpoly.kartenstapel.Kartenstapel;

public class KartenstapelImpl implements Kartenstapel
{
	private List<Karte> karten;
	private int kartenGezogen;

	public KartenstapelImpl(List<Karte> kartenstapel)
	{
		this.karten = kartenstapel;
		this.kartenGezogen = 0;
	}

	@Override
	public Karte ziehen()
	{
		Karte zuZiehen = karten.get(kartenGezogen);
		kartenGezogen++;

		// muss gemischt werden (wenn alle Karten aufgebraucht)
		if (karten.size() == kartenGezogen)
		{
			karten = mische(karten);
			kartenGezogen = 0;
		}

		return zuZiehen;
	}

	private static List<Karte> mische(List<Karte> karten)
	{
		List<Karte> kartenGemischt = new ArrayList<>();
		Random r = new Random();

		while (!karten.isEmpty())
		{
			Karte inNeuenStapel = karten.get(r.nextInt(karten.size()));
			kartenGemischt.add(inNeuenStapel);
			karten.remove(inNeuenStapel);
		}

		return kartenGemischt;
	}
}
