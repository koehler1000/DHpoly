package de.dhpoly.spieler.view;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.FeldStrasse;
import de.dhpoly.feld.control.FeldStrasseTest;
import de.dhpoly.spieler.control.SpielerImplTest;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.utils.Spielansicht;

public class StrassenbesitzeUIVorschau
{
	public static void main(String[] args)
	{
		Spieler spieler = SpielerImplTest.getDefaultSpieler();

		List<Feld> felder = new ArrayList<>();

		for (int i = 0; i < 5; i++)
		{
			FeldStrasse str = FeldStrasseTest.getDefaultStrasse("Str" + i);
			felder.add(str);
			str.kaufe(spieler);
		}

		Spielansicht.zeige(new StrassenbesitzeUI(spieler, Spielansicht.getSpielfeldAnsicht()));
	}
}
