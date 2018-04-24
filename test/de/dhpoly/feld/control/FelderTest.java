package de.dhpoly.feld.control;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.spiel.control.SpielImpl;
import de.dhpoly.spiel.control.SpielImplTest;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImplTest;

public class FelderTest
{
	@Test
	public void spielerStartetAufFeld0()
	{
		List<Feld> felder = new ArrayList<>();
		Felderverwaltung verwaltung = FelderverwaltungTest.getDefaultFelderverwaltung(felder);

		felder.add(getDefaultFeld(verwaltung));

		Spieler sp1 = SpielerImplTest.getDefaultSpieler();

		Assert.assertThat(sp1.getFeldNr(), Is.is(0));
	}

	@Test
	public void spielerKann2FelderLaufen() throws InterruptedException
	{
		List<Feld> felder = new ArrayList<>();
		Felderverwaltung verwaltung = FelderverwaltungTest.getDefaultFelderverwaltung(felder);

		felder.add(getDefaultFeld(verwaltung));
		felder.add(getDefaultFeld(verwaltung));
		felder.add(getDefaultFeld(verwaltung));
		felder.add(getDefaultFeld(verwaltung));

		SpielImpl spiel = SpielImplTest.getDefaultSpiel();
		spiel.setFelder(felder);

		Spieler sp1 = SpielerImplTest.getDefaultSpieler(spiel);
		spiel.fuegeSpielerHinzu(sp1);

		spiel.setAnimationen(false);

		Thread thread = spiel.rueckeThread(sp1, 2);
		thread.start();
		thread.join();

		Assert.assertThat(sp1.getFeldNr(), Is.is(2));
	}

	@Test
	public void spielerKannUeberrunden()
	{
		List<Feld> felder = new ArrayList<>();
		Felderverwaltung verwaltung = new FelderverwaltungImpl();
		verwaltung.setFelder(felder);

		felder.add(getDefaultFeld(verwaltung));
		felder.add(getDefaultFeld(verwaltung));
		felder.add(getDefaultFeld(verwaltung));
		felder.add(getDefaultFeld(verwaltung));

		SpielImpl spiel = SpielImplTest.getDefaultSpiel();
		spiel.setFelder(felder);

		Spieler sp1 = SpielerImplTest.getDefaultSpieler(spiel);
		spiel.fuegeSpielerHinzu(sp1);

		spiel.setAnimationen(false);

		spiel.ruecke(sp1, 4);
		Assert.assertThat(sp1.getFeldNr(), Is.is(0));
	}

	public static Feld getDefaultFeld(Felderverwaltung verwaltung)
	{
		return getDefaultFeld(verwaltung, 0);
	}

	public static Feld getDefaultFeld(Felderverwaltung verwaltung, int strassenGruppe)
	{
		return new FeldStrasse(verwaltung, 100, new int[] { 10, 20, 30, 40, 50 }, new ArrayList<>(), strassenGruppe,
				"test");
	}
}
