package de.dhpoly.spielfeld.model;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.FeldLosTest;
import de.dhpoly.feld.control.FeldStrasseTest;

public class Strassenspielfeld
{
	public List<Feld> getStandardSpielfeld()
	{
		List<Feld> standardfeld = new ArrayList<>();

		standardfeld.add(FeldLosTest.getDefaultFeld());

		for (int i = 0; i < 39; i++)
		{
			standardfeld.add(FeldStrasseTest.getDefaultStrasse("Test " + i));
		}

		return standardfeld;
	}
}
