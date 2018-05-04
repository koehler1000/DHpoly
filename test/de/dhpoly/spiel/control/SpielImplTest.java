package de.dhpoly.spiel.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.FeldLos;
import de.dhpoly.feld.control.FeldLosTest;
import de.dhpoly.feld.control.FeldStrasseTest;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImplTest;
import de.dhpoly.spieler.control.SpielerUnimplemented;

public class SpielImplTest
{
	private SpielImpl spiel;

	@Before
	public void vorbereitung()
	{
		List<Feld> felder = new ArrayList<>();
		felder.add(FeldLosTest.getDefaultFeld());
		felder.add(FeldStrasseTest.getDefaultStrasse());
		spiel = new SpielImpl();
		spiel.setFelder(felder);
		spiel.fuegeSpielerHinzu(SpielerImplTest.getDefaultSpieler("Test1", spiel));
		spiel.fuegeSpielerHinzu(SpielerImplTest.getDefaultSpieler("Test2", spiel));
	}

	@Test
	public void testaktuellerSpieler()
	{
		spiel.getAktuellerSpieler();
		assertEquals("Test1", spiel.getAktuellerSpieler().getDaten().getName());
	}

	@Test
	public void testnaechsterSpieler()
	{
		spiel.wuerfelWeitergeben(spiel.getAktuellerSpieler());
		assertEquals("Test2", spiel.getAktuellerSpieler().getDaten().getName());
	}

	@Test
	public void geldBeiUeberLos() throws InterruptedException
	{
		int geldVorDemLaufen = spiel.getAktuellerSpieler().getDaten().getRessourcenWert(Ressource.GELD);

		Thread thread = spiel.rueckeThread(spiel.getAktuellerSpieler(), 2);
		thread.start();
		thread.join();

		assertThat(spiel.getAktuellerSpieler().getDaten().getRessourcenWert(Ressource.GELD),
				Is.is(geldVorDemLaufen + new Einstellungen().getBetragPassierenLos()));
	}

	boolean hatVerloren = false;

	@Test
	public void spielerVerliertWennErAmEndeDesZugesKeinGeldMehrHat()
	{
		List<Feld> felder = new ArrayList<>();
		felder.add(FeldStrasseTest.getDefaultStrasse());
		Spiel spiel = SpielImplTest.getDefaultSpiel();
		spiel.setFelder(felder);

		spiel.fuegeSpielerHinzu(getSpieler(false));
		spiel.fuegeSpielerHinzu(getSpieler(true));

		assertThat(hatVerloren, Is.is(false));
		spiel.wuerfelWeitergeben(spiel.getAktuellerSpieler());

		assertThat(hatVerloren, Is.is(true));
	}

	boolean hatGewonnen = false;

	@Test
	public void spielerGewinntWennAlleAnderenVerlorenHaben()
	{
		List<Feld> felder = new ArrayList<>();
		felder.add(FeldStrasseTest.getDefaultStrasse());
		Spiel spiel = SpielImplTest.getDefaultSpiel();
		spiel.setFelder(felder);

		Spieler sieger = getSpieler(true);

		spiel.fuegeSpielerHinzu(getSpieler(false));
		spiel.fuegeSpielerHinzu(getSpieler(true));

		spiel.wuerfelWeitergeben(spiel.getAktuellerSpieler());

		assertThat(sieger.hatVerloren(), Is.is(false));
		assertThat(hatGewonnen, Is.is(true));
	}

	private Spieler getSpieler(boolean gewinntImmer)
	{
		return new SpielerUnimplemented()
		{
			@Override
			public void ausscheiden()
			{
				hatVerloren = true;
			}

			@Override
			public void gewonnen()
			{
				hatGewonnen = true;
			}
		};
	};

	public static SpielImpl getDefaultSpiel()
	{
		return getDefaultSpiel(new Einstellungen());
	}

	public static SpielImpl getDefaultSpiel(Einstellungen einstellungen)
	{
		List<Feld> felder = new ArrayList<>();
		felder.add(new FeldLos(einstellungen));

		SpielImpl spiel = new SpielImpl();
		spiel.setFelder(felder);

		return spiel;
	}
}
