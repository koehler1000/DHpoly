package de.dhpoly.kartenstapel.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.dhpoly.karte.Karte;
import de.dhpoly.karte.control.BezahlKarte;
import de.dhpoly.kartenstapel.Kartenstapel;

public class KartenstapelImpl implements Kartenstapel
{
	private List<BezahlKarte> karten;
	private int kartenGezogen;

	public KartenstapelImpl(List<BezahlKarte> karten)
	{
		this.karten = karten;
		this.kartenGezogen = 0;
	}

	@Override
	public Karte ziehen()
	{
		BezahlKarte zuZiehen = karten.get(kartenGezogen);
		kartenGezogen++;

		// muss gemischt werden (wenn alle Karten aufgebraucht)
		if (karten.size() == kartenGezogen)
		{
			karten = mische(karten);
			kartenGezogen = 0;
		}

		return zuZiehen;
	}

	private static List<BezahlKarte> mische(List<BezahlKarte> karten)
	{
		List<BezahlKarte> kartenGemischt = new ArrayList<>();
		Random r = new Random();

		while (karten.size() > 0)
		{
			BezahlKarte inNeuenStapel = karten.get(r.nextInt(karten.size()));
			kartenGemischt.add(inNeuenStapel);
			karten.remove(inNeuenStapel);
		}

		return kartenGemischt;
	}
}
