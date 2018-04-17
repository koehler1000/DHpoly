package de.dhpoly.spiel.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import de.dhpoly.einstellungen.model.EinstellungenImpl;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.Losfeld;
import de.dhpoly.feld.control.LosfeldTest;
import de.dhpoly.feld.control.Ressourcenfeld;
import de.dhpoly.feld.control.StrasseTest;
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
		felder.add(LosfeldTest.getDefaultFeld());
		felder.add(StrasseTest.getDefaultStrasse());
		spiel = new SpielImpl();
		spiel.setFelder(felder);
		spiel.fuegeSpielerHinzu(SpielerImplTest.getDefaultSpieler("Test1", spiel));
		spiel.fuegeSpielerHinzu(SpielerImplTest.getDefaultSpieler("Test2", spiel));
	}

	@Test
	public void testaktuellerSpieler()
	{
		spiel.getAktuellerSpieler();
		assertEquals("Test1", spiel.getAktuellerSpieler().getName());
	}

	@Test
	public void testnaechsterSpieler()
	{
		spiel.naechsterSpieler();
		assertEquals("Test2", spiel.getAktuellerSpieler().getName());
	}

	@Test
	public void geldBeiUeberLos() throws InterruptedException
	{
		int geldVorDemLaufen = spiel.getAktuellerSpieler().getRessourcenWerte(Ressource.GELD);

		Thread thread = spiel.rueckeThread(spiel.getAktuellerSpieler(), 2);
		thread.start();
		thread.join();

		assertThat(spiel.getAktuellerSpieler().getRessourcenWerte(Ressource.GELD),
				Is.is(geldVorDemLaufen + new EinstellungenImpl().getBetragPassierenLos()));
	}

	@Test
	public void ressourcenJedeRunde()
	{
		List<Feld> felder = new ArrayList<>();
		felder.add(StrasseTest.getDefaultStrasse());
		felder.add(StrasseTest.getDefaultStrasse());
		Ressourcenfeld feld = new Ressourcenfeld(Ressource.HOLZ);
		felder.add(feld);
		Ressourcenfeld feld2 = new Ressourcenfeld(Ressource.STEIN);
		felder.add(feld2);
		spiel = SpielImplTest.getDefaultSpiel();
		spiel.setFelder(felder);
		spiel.fuegeSpielerHinzu(SpielerImplTest.getDefaultSpieler("Test1", spiel));
		spiel.fuegeSpielerHinzu(SpielerImplTest.getDefaultSpieler("Test2", spiel));

		int holzVorErstemSpieler = spiel.getAktuellerSpieler().getRessourcenWerte(Ressource.HOLZ);
		int steinVorErstemSpieler = spiel.getAktuellerSpieler().getRessourcenWerte(Ressource.STEIN);

		for (int i = 0; i < spiel.getSpieler().size(); i++)
		{
			spiel.naechsterSpieler();
		}

		assertThat(spiel.getAktuellerSpieler().getRessourcenWerte(Ressource.HOLZ),
				Is.is(holzVorErstemSpieler + new EinstellungenImpl().getRessourcenErtrag()));
		assertThat(spiel.getAktuellerSpieler().getRessourcenWerte(Ressource.STEIN),
				Is.is(steinVorErstemSpieler + new EinstellungenImpl().getRessourcenErtrag()));
	}

	boolean hatVerloren = false;

	@Test
	public void spielerVerliertWennErAmEndeDesZugesKeinGeldMehrHat()
	{
		List<Feld> felder = new ArrayList<>();
		felder.add(StrasseTest.getDefaultStrasse());
		Spiel spiel = SpielImplTest.getDefaultSpiel();
		spiel.setFelder(felder);

		spiel.fuegeSpielerHinzu(getSpieler(false));
		spiel.fuegeSpielerHinzu(getSpieler(true));

		assertThat(hatVerloren, Is.is(false));
		spiel.naechsterSpieler();

		assertThat(hatVerloren, Is.is(true));
	}

	boolean hatGewonnen = false;

	@Test
	public void spielerGewinntWennAlleAnderenVerlorenHaben()
	{
		List<Feld> felder = new ArrayList<>();
		felder.add(StrasseTest.getDefaultStrasse());
		Spiel spiel = SpielImplTest.getDefaultSpiel();
		spiel.setFelder(felder);

		Spieler sieger = getSpieler(true);

		spiel.fuegeSpielerHinzu(getSpieler(false));
		spiel.fuegeSpielerHinzu(getSpieler(true));

		spiel.naechsterSpieler();

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

			@Override
			public boolean isNegative()
			{
				return !gewinntImmer;
			}
		};
	};

	public static SpielImpl getDefaultSpiel()
	{
		List<Feld> felder = new ArrayList<>();
		felder.add(new Losfeld(new EinstellungenImpl()));

		SpielImpl spiel = new SpielImpl();
		spiel.setFelder(felder);

		return spiel;
	}
}
