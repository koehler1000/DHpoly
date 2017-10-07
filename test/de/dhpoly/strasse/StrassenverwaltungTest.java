package de.dhpoly.strasse;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.dhpoly.spieler.Geldhaber;

public class StrassenverwaltungTest
{

	@Test
	public void nichtEigentuemerAllerStrassenWennStrassenNochNichtVerkauft()
	{
		Strassenverwaltung verwaltung = new Strassenverwaltung();

		List<Strasse> strassen = new ArrayList<>();
		verwaltung.setStrassen(strassen);

		strassen.add(new Strasse(verwaltung, 0, new int[] { 0, 0, 0, 0, 0, 0 }, 1, 1, "Badstraﬂe"));

		Geldhaber spieler = new Geldhaber(500);
		strassen.get(0).kaufe(spieler);

		assertTrue(verwaltung.isNutzerBesitzerAllerStrassen(1, spieler));
	}

	@Test
	public void bla()
	{
		Strassenverwaltung verwaltung = new Strassenverwaltung();

		List<Strasse> strassen = new ArrayList<>();
		verwaltung.setStrassen(strassen);

		strassen.add(new Strasse(verwaltung, 0, new int[] { 0, 0, 0, 0, 0, 0 }, 1, 1, "Badstraﬂe"));
		strassen.add(new Strasse(verwaltung, 0, new int[] { 0, 0, 0, 0, 0, 0 }, 1, 1, "Badstraﬂe"));

		Geldhaber spieler = new Geldhaber(500);
		strassen.get(0).kaufe(spieler);

		assertFalse(verwaltung.isNutzerBesitzerAllerStrassen(1, spieler));
	}

}
