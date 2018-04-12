package de.dhpoly.spielfeld.view;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.LosfeldTest;
import de.dhpoly.feld.control.StrasseTest;
import de.dhpoly.utils.Spielansicht;

public class SpielfeldUIVorschau
{
	public static void main(String[] args)
	{
		List<Feld> felder = new ArrayList<>();

		felder.add(LosfeldTest.getDefaultFeld());

		for (int i = 0; i < 39; i++)
		{
			felder.add(StrasseTest.getDefaultStrasse("Test " + i));
		}

		Spielansicht.zeige(new SpielfeldUI(felder, Spielansicht.getSpielfeldAnsicht()));
	}
}
