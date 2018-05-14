package de.dhpoly.spielfeld.view;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.FeldLosTest;
import de.dhpoly.feld.control.FeldStrasseTest;
import de.dhpoly.spiel.model.SpielfeldDaten;
import de.dhpoly.utils.Spielansicht;

public class SpielfeldUIVorschau
{
	public static void main(String[] args)
	{
		List<Feld> felder = new ArrayList<>();

		felder.add(FeldLosTest.getDefaultFeld());

		for (int i = 0; i < 39; i++)
		{
			felder.add(FeldStrasseTest.getDefaultStrasse("Test " + i));
		}

		Spielansicht.zeige(new SpielfeldUI(new SpielfeldDaten(felder), Spielansicht.getSpielfeldAnsicht()));
	}
}
