package de.dhpoly.handel.control;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.feld.control.FeldStrasse;
import de.dhpoly.feld.control.FeldStrasseTest;
import de.dhpoly.feld.control.FelderverwaltungTest;
import de.dhpoly.handel.Handel;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
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
		FeldStrasse feld = FeldStrasseTest.getDefaultStrasse(verwaltung, s1);
		felder.add(feld);

		Handel handel = new HandelImpl();

		Transaktion transaktion = new Transaktion(s1.getDaten(), s2.getDaten());
		transaktion.addDatensatzFelderwechsel(feld);
		handel.vorschlagAnnehmen(transaktion);

		assertTrue(feld.getEigentuemer().isPresent());
		assertThat(feld.getEigentuemer().get(), Is.is(s2));
	}

	@Test
	public void felderVerbuchenStrassenBekommen()
	{
		Felderverwaltung verwaltung = FelderverwaltungTest.getDefaultFelderverwaltung();

		Spieler s1 = SpielerImplTest.getDefaultSpieler();
		Spieler s2 = SpielerImplTest.getDefaultSpieler();

		FeldStrasse feld = FeldStrasseTest.getDefaultStrasse(verwaltung, s2);

		List<Feld> felder = new ArrayList<>();
		felder.add(feld);

		List<RessourcenDatensatz> datensaetze = new ArrayList<>();
		RessourcenDatensatz datensatz = new RessourcenDatensatz(Ressource.GELD, 50);
		datensaetze.add(datensatz);

		Handel handel = new HandelImpl();

		Transaktion transaktion = new Transaktion(s1.getDaten(), s2.getDaten());
		transaktion.addDatensatzFelderwechsel(feld);
		handel.vorschlagAnnehmen(transaktion);

		assertTrue(feld.getEigentuemer().isPresent());
		assertThat(feld.getEigentuemer().get(), Is.is(s1));
	}

	@Test
	public void felderVerbuchenGeldtransfer()
	{
		Spieler s1 = SpielerImplTest.getDefaultSpieler(150);
		Spieler s2 = SpielerImplTest.getDefaultSpieler(250);

		Handel handel = new HandelImpl();

		Transaktion transaktion = new Transaktion(s1.getDaten(), s2.getDaten());
		transaktion.setRessourcen(s2.getDaten(), Ressource.GELD, 50);
		handel.vorschlagAnnehmen(transaktion);

		assertThat(s1.getRessourcenWerte(Ressource.GELD), Is.is(200));
		assertThat(s2.getRessourcenWerte(Ressource.GELD), Is.is(200));
	}
}
