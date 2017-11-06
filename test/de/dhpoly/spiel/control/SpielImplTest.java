package de.dhpoly.spiel.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import de.dhpoly.einstellungen.control.EinstellungenImpl;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.Ressourcenfeld;
import de.dhpoly.feld.control.StrasseTest;
import de.dhpoly.feld.model.Ressource;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImplTest;

public class SpielImplTest
{

	private Spiel spiel;

	@Before
	public void vorbereitung()
	{
		List<Spieler> spieler = new ArrayList<Spieler>();
		spieler.add(SpielerImplTest.getDefaultSpieler("Test1", spiel));
		spieler.add(SpielerImplTest.getDefaultSpieler("Test2", spiel));

		List<Feld> felder = new ArrayList<>();
		felder.add(StrasseTest.getDefaultStrasse());
		felder.add(StrasseTest.getDefaultStrasse());
		spiel = new SpielImpl(felder, spieler, new EinstellungenImpl());
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
	public void geldBeiUeberLos()
	{
		int geldVorDemLaufen = spiel.getAktuellerSpieler().getBargeld();

		spiel.ruecke(spiel.getAktuellerSpieler(), 2);

		assertThat(spiel.getAktuellerSpieler().getBargeld(),
				Is.is(geldVorDemLaufen + new EinstellungenImpl().getBetragPassierenLos()));
	}

	@Test
	public void ressourcenJedeRunde()
	{
		List<Spieler> spieler = new ArrayList<Spieler>();
		spieler.add(SpielerImplTest.getDefaultSpieler("Test1", spiel));
		spieler.add(SpielerImplTest.getDefaultSpieler("Test2", spiel));

		List<Feld> felder = new ArrayList<>();
		felder.add(StrasseTest.getDefaultStrasse());
		felder.add(StrasseTest.getDefaultStrasse());
		Ressourcenfeld feld = new Ressourcenfeld(Ressource.HOLZ);
		felder.add(feld);
		Ressourcenfeld feld2 = new Ressourcenfeld(Ressource.STEIN);
		felder.add(feld2);
		spiel = new SpielImpl(felder, spieler, new EinstellungenImpl());

		int holzVorErstemSpieler = spiel.getAktuellerSpieler().getHolzVorrat();
		int steinVorErstemSpieler = spiel.getAktuellerSpieler().getSteinVorrat();

		for (int i = 0; i < spieler.size(); i++)
		{
			spiel.naechsterSpieler();
		}

		assertThat(spiel.getAktuellerSpieler().getHolzVorrat(),
				Is.is(holzVorErstemSpieler + new EinstellungenImpl().getRessourcenErtrag()));
		assertThat(spiel.getAktuellerSpieler().getSteinVorrat(),
				Is.is(steinVorErstemSpieler + new EinstellungenImpl().getRessourcenErtrag()));
	}
}
