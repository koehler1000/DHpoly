package de.dhpoly.handel.control;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.FeldStrasse;
import de.dhpoly.feld.control.FeldStrasseTest;
import de.dhpoly.feld.model.FeldDaten;
import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.handel.Handel;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielImpl;
import de.dhpoly.spieler.control.SpielerImplTest;
import de.dhpoly.spieler.model.Spieler;

public class HandelImplTest
{
	Spieler s1 = SpielerImplTest.getDefaultSpieler(150);
	Spieler s2 = SpielerImplTest.getDefaultSpieler(250);

	private Spiel spiel = new SpielImpl();

	@Test
	public void felderVerbuchenStrassenGeben()
	{
		s1 = SpielerImplTest.getDefaultSpieler();
		s2 = SpielerImplTest.getDefaultSpieler();

		spiel.fuegeSpielerHinzu(s1);
		spiel.fuegeSpielerHinzu(s2);

		List<FeldDaten> felder = new ArrayList<>();
		StrasseDaten strasse = new StrasseDaten();

		s1.einzahlen(new RessourcenDatensatz(Ressource.GELD, strasse.getKaufpreis()));

		felder.add(strasse);

		spiel.setFelder(felder);
		spiel.starteSpiel();

		strasse.setEigentuemer(s1);

		assertThat(strasse.getEigentuemer().get(), Is.is(s1));

		Handel handel = new HandelImpl();

		Transaktion transaktion = new Transaktion(s1, s2);
		transaktion.addDatensatzFelderwechsel(strasse);

		handel.vorschlagAnnehmen(transaktion, spiel);

		assertTrue(strasse.getEigentuemer().isPresent());
		assertThat(strasse.getEigentuemer().get(), Is.is(s2));
	}

	@Test
	public void felderVerbuchenStrassenBekommen()
	{
		s1 = SpielerImplTest.getDefaultSpieler();
		s2 = SpielerImplTest.getDefaultSpieler();

		spiel.fuegeSpielerHinzu(s1);
		spiel.fuegeSpielerHinzu(s2);

		FeldStrasse feld = FeldStrasseTest.getDefaultStrasse(s2);
		StrasseDaten strasse = new StrasseDaten();

		List<Feld> felder = new ArrayList<>();
		felder.add(feld);

		List<RessourcenDatensatz> datensaetze = new ArrayList<>();
		RessourcenDatensatz datensatz = new RessourcenDatensatz(Ressource.GELD, 50);
		datensaetze.add(datensatz);

		Handel handel = new HandelImpl();

		Transaktion transaktion = new Transaktion(s1, s2);
		transaktion.addDatensatzFelderwechsel(strasse);
		handel.vorschlagAnnehmen(transaktion, spiel);

		assertTrue(strasse.getEigentuemer().isPresent());
		assertThat(strasse.getEigentuemer().get(), Is.is(s1));
	}

	@Test
	public void felderVerbuchenGeldtransfer()
	{
		Handel handel = new HandelImpl();

		spiel.fuegeSpielerHinzu(s1);
		spiel.fuegeSpielerHinzu(s2);

		Transaktion transaktion = new Transaktion(s1, s2);
		transaktion.setRessourcen(s2, Ressource.GELD, 50);
		handel.vorschlagAnnehmen(transaktion, spiel);

		assertThat(s1.getRessourcenWert(Ressource.GELD), Is.is(200));
		assertThat(s2.getRessourcenWert(Ressource.GELD), Is.is(200));
	}
}