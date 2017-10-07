package de.dhpoly.feld;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.feld.Strasse;
import de.dhpoly.spielfeld.Spiel;

public class FelderTest
{

	@Test
	public void laufen()
	{
		List<Feld> felder = new ArrayList<>();
		Felderverwaltung verwaltung = new Felderverwaltung();
		verwaltung.setFelder(felder);

		felder.add(new Strasse(verwaltung, 100, new int[] { 10, 20, 30, 40, 50 }, 1, 1, "test"));

		Spiel spiel = new Spiel(felder);
	}

}
