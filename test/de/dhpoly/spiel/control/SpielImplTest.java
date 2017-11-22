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
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.control.SpielerImplTest;
import de.dhpoly.wuerfel.control.WuerfelImpl;

public class SpielImplTest
{
	private SpielImpl spiel;

	@Before
	public void vorbereitung()
	{
		List<Feld> felder = new ArrayList<>();
		felder.add(StrasseTest.getDefaultStrasse());
		felder.add(StrasseTest.getDefaultStrasse());
		spiel = new SpielImpl(felder, new EinstellungenImpl(), new WuerfelImpl());
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
	public void geldBeiUeberLos()
	{
		int geldVorDemLaufen = spiel.getAktuellerSpieler().getRessourcenWerte(Ressource.GELD);

		spiel.ruecke(spiel.getAktuellerSpieler(), 2);

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
		spiel = new SpielImpl(felder, new EinstellungenImpl(), new WuerfelImpl());
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
}
