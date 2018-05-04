package de.dhpoly.kartenverbucher.control;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.karte.model.BezahlKarte;
import de.dhpoly.kartenstapel.model.GeldTransfer;
import de.dhpoly.kartenverbucher.Kartenverbucher;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImplTest;

public class KartenverbucherImplTest
{
	@Test
	public void geldVonBank()
	{
		final int startgeld = 500;
		final int transferbetrag = 50;

		Spieler spieler = SpielerImplTest.getDefaultSpieler(startgeld);
		BezahlKarte karte = new BezahlKarte("bla", GeldTransfer.BANK_SPIELER,
				new RessourcenDatensatz(Ressource.GELD, transferbetrag));

		Kartenverbucher verbucher = new KartenverbucherImpl();
		verbucher.bewegeGeld(karte, null, spieler);

		assertThat(spieler.getDaten().getRessourcenWert(Ressource.GELD), Is.is(startgeld + transferbetrag));
	}

	@Test
	public void geldVonAnderenSpielern()
	{
		Spiel spiel = null;

		Spieler ziehenderSpieler = SpielerImplTest.getDefaultSpieler(500, spiel);

		List<Spieler> spieler = new ArrayList<>();
		spieler.add(ziehenderSpieler);
		spieler.add(SpielerImplTest.getDefaultSpieler(100, spiel));
		spieler.add(SpielerImplTest.getDefaultSpieler(100, spiel));

		BezahlKarte karte = new BezahlKarte("bla", GeldTransfer.ANDERESPIELER_SPIELER,
				new RessourcenDatensatz(Ressource.GELD, 50));

		Kartenverbucher verbucher = new KartenverbucherImpl();
		verbucher.bewegeGeld(karte, spieler, ziehenderSpieler);

		assertThat(ziehenderSpieler.getDaten().getRessourcenWert(Ressource.GELD), Is.is(600));

		assertThat(spieler.get(1).getDaten().getRessourcenWert(Ressource.GELD), Is.is(50));
		assertThat(spieler.get(2).getDaten().getRessourcenWert(Ressource.GELD), Is.is(50));
	}

	@Test
	public void geldAnAndereSpielern()
	{
		Spiel spiel = null;

		Spieler ziehenderSpieler = SpielerImplTest.getDefaultSpieler(500, spiel);

		List<Spieler> spieler = new ArrayList<>();
		spieler.add(ziehenderSpieler);
		spieler.add(SpielerImplTest.getDefaultSpieler(100, spiel));
		spieler.add(SpielerImplTest.getDefaultSpieler(100, spiel));

		BezahlKarte karte = new BezahlKarte("bla", GeldTransfer.SPIELER_ANDERESPIELER,
				new RessourcenDatensatz(Ressource.GELD, 50));

		Kartenverbucher verbucher = new KartenverbucherImpl();
		verbucher.bewegeGeld(karte, spieler, ziehenderSpieler);

		assertThat(ziehenderSpieler.getDaten().getRessourcenWert(Ressource.GELD), Is.is(400));

		assertThat(spieler.get(1).getDaten().getRessourcenWert(Ressource.GELD), Is.is(150));
		assertThat(spieler.get(2).getDaten().getRessourcenWert(Ressource.GELD), Is.is(150));
	}

}
