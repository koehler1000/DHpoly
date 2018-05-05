package de.dhpoly.feld.control;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.spieler.control.SpielerImplTest;
import de.dhpoly.spieler.model.Spieler;

public class FelderverwaltungTest
{

	@Test
	public void nichtEigentuemerAllerStrassenWennStrassenNochNichtVerkauft()
	{
		List<Feld> felder = new ArrayList<>();
		Felderverwaltung verwaltung = getDefaultFelderverwaltung(felder);

		felder.add(FelderTest.getDefaultFeld(verwaltung, 1));

		Spieler spieler = SpielerImplTest.getDefaultSpieler();

		FeldStrasse strasse = (FeldStrasse) felder.get(0);
		strasse.kaufe(spieler);

		assertTrue(verwaltung.isNutzerBesitzerAllerStrassen(1, spieler));
	}

	@Test
	public void spielerBesitztNichtAlleStrassen()
	{
		List<Feld> felder = new ArrayList<>();
		Felderverwaltung verwaltung = getDefaultFelderverwaltung(felder);

		felder.add(FelderTest.getDefaultFeld(verwaltung, 1));
		felder.add(FelderTest.getDefaultFeld(verwaltung, 1));

		Spieler spieler = SpielerImplTest.getDefaultSpieler();
		FeldStrasse strasse = (FeldStrasse) felder.get(0);
		strasse.kaufe(spieler);

		assertFalse(verwaltung.isNutzerBesitzerAllerStrassen(1, spieler));
	}

	public static Felderverwaltung getDefaultFelderverwaltung()
	{
		return new FelderverwaltungImpl();
	}

	public static Felderverwaltung getDefaultFelderverwaltung(List<Feld> felder)
	{
		Felderverwaltung verwaltung = getDefaultFelderverwaltung();
		verwaltung.setFelder(felder);
		return verwaltung;
	}
}
