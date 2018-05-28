package de.dhpoly.kartenstapel.control;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.dhpoly.karte.model.Karte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.karte.model.WetterKarte;

public class KartenstapelImplTest
{
	@Test
	public void vorMischelnZuerstAlleKarten() throws Exception
	{
		List<Karte> karten = new ArrayList<>();
		karten.add(new WetterKarte(Wetter.BEWOELKT));
		karten.add(new WetterKarte(Wetter.SONNE));
		karten.add(new WetterKarte(Wetter.REGEN));

		KartenstapelImpl kartenstapel = new KartenstapelImpl(karten);

		List<Karte> gezogeneKarten = new ArrayList<>();
		for (int i = 0; i < karten.size(); i++)
		{
			Karte gezogen = kartenstapel.ziehen();
			if (gezogeneKarten.contains(gezogen))
			{
				fail("Neue Karte trotz noch vorhandenen Karten");
			}
			gezogeneKarten.add(gezogen);
		}

		assertTrue(gezogeneKarten.contains(kartenstapel.ziehen()));
	}
}
