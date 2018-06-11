package de.dhpoly.kartenverbucher.control;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.karte.model.BezahlKarte;
import de.dhpoly.kartenstapel.model.BezahlZiel;
import de.dhpoly.kartenverbucher.Kartenverbucher;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.control.SpielerImplTest;
import de.dhpoly.spieler.model.Spieler;

public class KartenverbucherImplTest
{
	@Test
	public void geldVonBank()
	{
		final int startgeld = 500;
		final int transferbetrag = 50;

		Spieler spieler = SpielerImplTest.getDefaultSpieler(startgeld);
		BezahlKarte karte = new BezahlKarte("bla", BezahlZiel.BANK, BezahlZiel.ALLE_SPIELER,
				new RessourcenDatensatz(Ressource.GELD, transferbetrag));

		List<Spieler> alleSpieler = new ArrayList<>();
		alleSpieler.add(spieler);
		Kartenverbucher verbucher = new KartenverbucherImpl();
		verbucher.bewegeGeld(karte, alleSpieler, spieler);

		assertThat(spieler.getRessourcenWert(Ressource.GELD), Is.is(startgeld + transferbetrag));
	}

	@Test
	public void geldVonAnderenSpielern()
	{
		Spieler ziehenderSpieler = SpielerImplTest.getDefaultSpieler(500);

		List<Spieler> spieler = new ArrayList<>();
		spieler.add(ziehenderSpieler);
		spieler.add(SpielerImplTest.getDefaultSpieler(100));
		spieler.add(SpielerImplTest.getDefaultSpieler(100));

		BezahlKarte karte = new BezahlKarte("bla", BezahlZiel.SPIELER_ANDERE, BezahlZiel.SPIELER_ZIEHER,
				new RessourcenDatensatz(Ressource.GELD, 50));

		Kartenverbucher verbucher = new KartenverbucherImpl();
		verbucher.bewegeGeld(karte, spieler, ziehenderSpieler);

		assertThat(ziehenderSpieler.getRessourcenWert(Ressource.GELD), Is.is(600));

		assertThat(spieler.get(1).getRessourcenWert(Ressource.GELD), Is.is(50));
		assertThat(spieler.get(2).getRessourcenWert(Ressource.GELD), Is.is(50));
	}

	@Test
	public void geldAnAndereSpielern()
	{
		Spieler ziehenderSpieler = SpielerImplTest.getDefaultSpieler(500);

		List<Spieler> spieler = new ArrayList<>();
		spieler.add(ziehenderSpieler);
		spieler.add(SpielerImplTest.getDefaultSpieler(100));
		spieler.add(SpielerImplTest.getDefaultSpieler(100));

		BezahlKarte karte = new BezahlKarte("bla", BezahlZiel.SPIELER_ZIEHER, BezahlZiel.SPIELER_ANDERE,
				new RessourcenDatensatz(Ressource.GELD, 50));

		Kartenverbucher verbucher = new KartenverbucherImpl();
		verbucher.bewegeGeld(karte, spieler, ziehenderSpieler);

		assertThat(ziehenderSpieler.getRessourcenWert(Ressource.GELD), Is.is(400));

		assertThat(spieler.get(1).getRessourcenWert(Ressource.GELD), Is.is(150));
		assertThat(spieler.get(2).getRessourcenWert(Ressource.GELD), Is.is(150));
	}

}
