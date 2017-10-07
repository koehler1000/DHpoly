package de.dhpoly.feld;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.feld.Strasse;
import de.dhpoly.spieler.Geldhaber;

public class FelderverwaltungTest
{

	@Test
	public void nichtEigentuemerAllerStrassenWennStrassenNochNichtVerkauft()
	{
		Felderverwaltung verwaltung = new Felderverwaltung();

		List<Feld> felder = new ArrayList<>();
		verwaltung.setFelder(felder);

		felder.add(new Strasse(verwaltung, 0, new int[] { 0, 0, 0, 0, 0, 0 }, 1, 1, "Badstraﬂe"));

		Geldhaber spieler = new Geldhaber(500);
		felder.get(0).kaufe(spieler);

		assertTrue(verwaltung.isNutzerBesitzerAllerStrassen(1, spieler));
	}

	@Test
	public void spielerBesitztNichtAlleStrassen()
	{
		Felderverwaltung verwaltung = new Felderverwaltung();

		List<Feld> felder = new ArrayList<>();
		verwaltung.setFelder(felder);

		felder.add(new Strasse(verwaltung, 0, new int[] { 0, 0, 0, 0, 0, 0 }, 1, 1, "Badstraﬂe"));
		felder.add(new Strasse(verwaltung, 0, new int[] { 0, 0, 0, 0, 0, 0 }, 1, 1, "Badstraﬂe"));

		Geldhaber spieler = new Geldhaber(500);
		felder.get(0).kaufe(spieler);

		assertFalse(verwaltung.isNutzerBesitzerAllerStrassen(1, spieler));
	}

}
