package de.dhpoly.spielfeld.control;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.feld.Strasse;
import de.dhpoly.spielfeld.Spielfeld;

public class SpielfeldImpl implements Spielfeld
{

	@Override
	public List<Feld> getStandardSpielfeld()
	{
		Felderverwaltung verwaltung = new Felderverwaltung();

		List<Feld> felder = new ArrayList<>();
		felder.add(new Strasse(verwaltung, 50, new int[] { 10, 20, 30, 50, 70, 90 }, 1, 3, "Badstrasse"));
		felder.add(new Strasse(verwaltung, 50, new int[] { 10, 20, 30, 50, 70, 90 }, 1, 3, "Badstrasse2"));
		felder.add(new Strasse(verwaltung, 50, new int[] { 10, 20, 30, 50, 70, 90 }, 1, 3, "Badstrasse3"));
		felder.add(new Strasse(verwaltung, 50, new int[] { 10, 20, 30, 50, 70, 90 }, 1, 3, "Badstrasse4"));
		felder.add(new Strasse(verwaltung, 50, new int[] { 10, 20, 30, 50, 70, 90 }, 1, 3, "Badstrasse5"));
		felder.add(new Strasse(verwaltung, 50, new int[] { 10, 20, 30, 50, 70, 90 }, 1, 3, "Badstrasse6"));
		felder.add(new Strasse(verwaltung, 50, new int[] { 10, 20, 30, 50, 70, 90 }, 1, 3, "Badstrasse7"));
		felder.add(new Strasse(verwaltung, 50, new int[] { 10, 20, 30, 50, 70, 90 }, 1, 3, "Badstrasse8"));
		felder.add(new Strasse(verwaltung, 50, new int[] { 10, 20, 30, 50, 70, 90 }, 1, 3, "Badstrasse9"));

		return felder;
	}
}
