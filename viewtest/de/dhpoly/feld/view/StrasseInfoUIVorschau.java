package de.dhpoly.feld.view;

import de.dhpoly.feld.control.FeldStrasseTest;
import de.dhpoly.utils.Spielansicht;

public class StrasseInfoUIVorschau
{
	public static void main(String[] args)
	{
		Spielansicht.zeige(new StrasseInfoUI(FeldStrasseTest.getDefaultStrasse(), Spielansicht.getSpielfeldAnsicht()));
	}
}
