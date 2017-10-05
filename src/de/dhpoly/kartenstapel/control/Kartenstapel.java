package de.dhpoly.kartenstapel.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.monopoly.kartenstapel.model.Karte;

public class Kartenstapel
{
	private List<Karte> karten;
	private int kartenGezogen;
	
	public Kartenstapel(List<Karte> karten)
	{
		this.karten = karten;
		this.kartenGezogen = 0;
	}

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

		while (karten.size() > 0)
		{
			Karte inNeuenStapel = karten.get(r.nextInt(karten.size()));
			kartenGemischt.add(inNeuenStapel);
			karten.remove(inNeuenStapel);
		}

		return kartenGemischt;
	}
}
