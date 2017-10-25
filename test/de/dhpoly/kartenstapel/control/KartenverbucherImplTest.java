package de.dhpoly.kartenstapel.control;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.kartenstapel.Kartenverbucher;
import de.dhpoly.kartenstapel.model.GeldTransfer;
import de.dhpoly.kartenstapel.model.Karte;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImpl;

public class KartenverbucherImplTest
{
	@Test
	public void geldVonBank()
	{
		Spieler spieler = new SpielerImpl("foo", 500);
		Karte karte = new Karte("bla", GeldTransfer.BANK_SPIELER, 50);

		Kartenverbucher verbucher = new KartenverbucherImpl();
		verbucher.bewegeGeld(karte, null, spieler, null);

		assertThat(spieler.getBargeld(), Is.is(550));
	}

	@Test
	public void geldAnFreiparken()
	{
		Spieler spieler = new SpielerImpl("foo", 500);
		Spieler freiparken = new SpielerImpl("bar", 0);

		Karte karte = new Karte("bla", GeldTransfer.SPIELER_FREIPARKEN, 50);

		Kartenverbucher verbucher = new KartenverbucherImpl();
		verbucher.bewegeGeld(karte, null, spieler, freiparken);

		assertThat(spieler.getBargeld(), Is.is(450));
		assertThat(freiparken.getBargeld(), Is.is(50));
	}

	@Test
	public void geldVonAnderenSpielern()
	{
		Spieler ziehenderSpieler = new SpielerImpl("foo", 500);

		List<Spieler> spieler = new ArrayList<>();
		spieler.add(ziehenderSpieler);
		spieler.add(new SpielerImpl("bar", 100));
		spieler.add(new SpielerImpl("ich", 100));

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
		SpielerImpl ziehenderSpieler = new SpielerImpl("me", 500);

		List<Spieler> spieler = new ArrayList<>();
		spieler.add(ziehenderSpieler);
		spieler.add(new SpielerImpl("foo", 100));
		spieler.add(new SpielerImpl("bar", 100));

		Karte karte = new Karte("bla", GeldTransfer.SPIELER_ANDERESPIELER, 50);

		Kartenverbucher verbucher = new KartenverbucherImpl();
		verbucher.bewegeGeld(karte, spieler, ziehenderSpieler, null);

		assertThat(ziehenderSpieler.getBargeld(), Is.is(400));

		assertThat(spieler.get(1).getBargeld(), Is.is(150));
		assertThat(spieler.get(2).getBargeld(), Is.is(150));
	}

}
