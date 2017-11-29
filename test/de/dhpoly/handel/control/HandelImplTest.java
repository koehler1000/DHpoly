package de.dhpoly.handel.control;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.feld.control.FelderverwaltungTest;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.feld.control.StrasseTest;
import de.dhpoly.handel.Handel;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.control.RessourcenDatensatzImpl;
import de.dhpoly.ressource.model.Ressource;
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

		List<Feld> felder = new ArrayList<>();
		Strasse feld = StrasseTest.getDefaultStrasse(verwaltung, s1);
		felder.add(feld);

		Handel handel = new HandelImpl();
		handel.vorschlagAnnehmen(
				new Transaktion(felder, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), s1, s2));

		assertTrue(feld.getEigentuemer().isPresent());
		assertThat(feld.getEigentuemer().get(), Is.is(s2));
	}

	@Test
	public void felderVerbuchenStrassenBekommen()
	{
		Felderverwaltung verwaltung = FelderverwaltungTest.getDefaultFelderverwaltung();

		Spieler s1 = SpielerImplTest.getDefaultSpieler();
		Spieler s2 = SpielerImplTest.getDefaultSpieler();

		Strasse feld = StrasseTest.getDefaultStrasse(verwaltung, s2);

		List<Feld> felder = new ArrayList<>();
		felder.add(feld);

		List<RessourcenDatensatz> datensaetze = new ArrayList<>();
		RessourcenDatensatz datensatz = new RessourcenDatensatzImpl(Ressource.GELD, 50);
		datensaetze.add(datensatz);

		Handel handel = new HandelImpl();
		handel.vorschlagAnnehmen(
				new Transaktion(new ArrayList<>(), felder, new ArrayList<>(), new ArrayList<>(), s1, s2));

		assertTrue(feld.getEigentuemer().isPresent());
		assertThat(feld.getEigentuemer().get(), Is.is(s1));
	}

	@Test
	public void felderVerbuchenGeldtransfer()
	{
		Spieler s1 = SpielerImplTest.getDefaultSpieler(150);
		Spieler s2 = SpielerImplTest.getDefaultSpieler(250);

		List<RessourcenDatensatz> datensaetze = new ArrayList<>();
		RessourcenDatensatz datensatz = new RessourcenDatensatzImpl(Ressource.GELD, 50);
		datensaetze.add(datensatz);

		Handel handel = new HandelImpl();
		handel.vorschlagAnnehmen(
				new Transaktion(new ArrayList<>(), new ArrayList<>(), datensaetze, new ArrayList<>(), s2, s1));

		assertThat(s1.getRessourcenWerte(Ressource.GELD), Is.is(200));
		assertThat(s2.getRessourcenWerte(Ressource.GELD), Is.is(200));
	}
}
