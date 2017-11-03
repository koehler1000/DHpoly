package de.dhpoly.handel.control;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.feld.control.FelderTest;
import de.dhpoly.feld.control.FelderverwaltungTest;
import de.dhpoly.handel.Handel;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImplTest;

public class HandelImplTest
{

	@Test
	public void felderVerbuchenStrassenGeben()
	{
		Felderverwaltung verwaltung = FelderverwaltungTest.getDefaultFelderverwaltung();

		Spieler s1 = SpielerImplTest.getDefaultSpieler();
		Spieler s2 = SpielerImplTest.getDefaultSpieler();

		Feld feld = FelderTest.getDefaultFeld(verwaltung, s1);

		List<Feld> felder = new ArrayList<>();
		felder.add(feld);

		Handel handel = new HandelImpl();
		handel.vorschlagAnnehmen(new Transaktion(0, felder, new ArrayList<>(), s1, s2));

		assertTrue(feld.getEigentuemer().isPresent());
		assertThat(feld.getEigentuemer().get(), Is.is(s2));
	}

	@Test
	public void felderVerbuchenStrassenBekommen()
	{
		Felderverwaltung verwaltung = FelderverwaltungTest.getDefaultFelderverwaltung();

		Spieler s1 = SpielerImplTest.getDefaultSpieler();
		Spieler s2 = SpielerImplTest.getDefaultSpieler();

		Feld feld = FelderTest.getDefaultFeld(verwaltung, s1);

		List<Feld> felder = new ArrayList<>();
		felder.add(feld);

		Handel handel = new HandelImpl();
		handel.vorschlagAnnehmen(new Transaktion(0, new ArrayList<>(), felder, s2, s1));

		assertTrue(feld.getEigentuemer().isPresent());
		assertThat(feld.getEigentuemer().get(), Is.is(s2));
	}

	@Test
	public void felderVerbuchenGeldtransfer()
	{
		Spieler s1 = SpielerImplTest.getDefaultSpieler(150);
		Spieler s2 = SpielerImplTest.getDefaultSpieler(250);

		Handel handel = new HandelImpl();
		handel.vorschlagAnnehmen(new Transaktion(50, new ArrayList<>(), new ArrayList<>(), s2, s1));

		assertThat(s1.getBargeld(), Is.is(200));
		assertThat(s2.getBargeld(), Is.is(200));
	}
}
