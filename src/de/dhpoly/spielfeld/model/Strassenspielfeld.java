package de.dhpoly.spielfeld.model;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.LosfeldTest;
import de.dhpoly.feld.control.StrasseTest;

public class Strassenspielfeld
{
	public List<Feld> getStandardSpielfeld()
	{
		List<Feld> standardfeld = new ArrayList<>();

		standardfeld.add(LosfeldTest.getDefaultFeld());

		for (int i = 0; i < 39; i++)
		{
			standardfeld.add(StrasseTest.getDefaultStrasse("Test " + i));
		}

		return standardfeld;
	}
}
