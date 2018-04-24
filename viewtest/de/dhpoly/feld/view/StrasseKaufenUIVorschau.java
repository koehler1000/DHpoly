package de.dhpoly.feld.view;

import de.dhpoly.feld.control.FeldStrasseTest;
import de.dhpoly.spieler.control.SpielerImplTest;
import de.dhpoly.utils.Spielansicht;

public class StrasseKaufenUIVorschau
{
	public static void main(String[] args)
	{
		Spielansicht.zeige(new StrasseKaufenUI(FeldStrasseTest.getDefaultStrasse(), SpielerImplTest.getDefaultSpieler(),
				Spielansicht.getSpielfeldAnsicht()));
	}
}
