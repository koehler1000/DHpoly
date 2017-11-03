package de.dhpoly.feld.control;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImpl;

public class FelderverwaltungTest
{

	@Test
	public void nichtEigentuemerAllerStrassenWennStrassenNochNichtVerkauft()
	{
		Felderverwaltung verwaltung = new FelderverwaltungImpl();

		List<Feld> felder = new ArrayList<>();
		verwaltung.setFelder(felder);

		felder.add(new Strasse(verwaltung, 0, new int[] { 0, 0, 0, 0, 0, 0 }, 1, 1, "Badstraﬂe"));

		Spieler spieler = new SpielerImpl("ich", 500);
		felder.get(0).kaufe(spieler);

		assertTrue(verwaltung.isNutzerBesitzerAllerStrassen(1, spieler));
	}

	@Test
	public void spielerBesitztNichtAlleStrassen()
	{
		Felderverwaltung verwaltung = new FelderverwaltungImpl();

		List<Feld> felder = new ArrayList<>();
		verwaltung.setFelder(felder);

		felder.add(new Strasse(verwaltung, 0, new int[] { 0, 0, 0, 0, 0, 0 }, 1, 1, "Badstraﬂe"));
		felder.add(new Strasse(verwaltung, 0, new int[] { 0, 0, 0, 0, 0, 0 }, 1, 1, "Badstraﬂe"));

		Spieler spieler = new SpielerImpl("foo", 500);
		felder.get(0).kaufe(spieler);

		assertFalse(verwaltung.isNutzerBesitzerAllerStrassen(1, spieler));
	}

	public static Felderverwaltung getDefaultFelderverwaltung()
	{
		return new FelderverwaltungImpl();
	}
}
