package de.dhpoly.kartenstapel.control;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.kartenstapel.Kartenverbucher;
import de.dhpoly.kartenstapel.control.KartenverbucherImpl;
import de.dhpoly.kartenstapel.model.GeldTransfer;
import de.dhpoly.kartenstapel.model.Karte;
import de.dhpoly.spieler.Geldhaber;
import de.dhpoly.spieler.model.GeldhaberImpl;

public class KartenverbucherImplTest
{
	@Test
	public void geldVonBank()
	{
		Geldhaber spieler = new GeldhaberImpl(500);
		Karte karte = new Karte("bla", GeldTransfer.BANK_SPIELER, 50);

		Kartenverbucher verbucher = new KartenverbucherImpl();
		verbucher.bewegeGeld(karte, null, spieler, null);

		assertThat(spieler.getBargeld(), Is.is(550));
	}

	@Test
	public void geldAnFreiparken()
	{
		Geldhaber spieler = new GeldhaberImpl(500);
		Geldhaber freiparken = new GeldhaberImpl(0);

		Karte karte = new Karte("bla", GeldTransfer.SPIELER_FREIPARKEN, 50);

		Kartenverbucher verbucher = new KartenverbucherImpl();
		verbucher.bewegeGeld(karte, null, spieler, freiparken);

		assertThat(spieler.getBargeld(), Is.is(450));
		assertThat(freiparken.getBargeld(), Is.is(50));
	}

	@Test
	public void geldVonAnderenSpielern()
	{
		Geldhaber ziehenderSpieler = new GeldhaberImpl(500);

		List<Geldhaber> spieler = new ArrayList<>();
		spieler.add(ziehenderSpieler);
		spieler.add(new GeldhaberImpl(100));
		spieler.add(new GeldhaberImpl(100));

		Karte karte = new Karte("bla", GeldTransfer.ANDERESPIELER_SPIELER, 50);

		Kartenverbucher verbucher = new KartenverbucherImpl();
		verbucher.bewegeGeld(karte, spieler, ziehenderSpieler, null);

		assertThat(ziehenderSpieler.getBargeld(), Is.is(600));

		assertThat(spieler.get(1).getBargeld(), Is.is(50));
		assertThat(spieler.get(2).getBargeld(), Is.is(50));
	}

	@Test
	public void geldAnAndereSpielern()
	{
		GeldhaberImpl ziehenderSpieler = new GeldhaberImpl(500);

		List<Geldhaber> spieler = new ArrayList<>();
		spieler.add(ziehenderSpieler);
		spieler.add(new GeldhaberImpl(100));
		spieler.add(new GeldhaberImpl(100));

		Karte karte = new Karte("bla", GeldTransfer.SPIELER_ANDERESPIELER, 50);

		Kartenverbucher verbucher = new KartenverbucherImpl();
		verbucher.bewegeGeld(karte, spieler, ziehenderSpieler, null);

		assertThat(ziehenderSpieler.getBargeld(), Is.is(400));

		assertThat(spieler.get(1).getBargeld(), Is.is(150));
		assertThat(spieler.get(2).getBargeld(), Is.is(150));
	}

}
