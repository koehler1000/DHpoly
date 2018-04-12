package de.dhpoly.feld.view;

import de.dhpoly.feld.control.StrasseTest;
import de.dhpoly.spieler.control.SpielerImplTest;
import de.dhpoly.utils.Spielansicht;

public class StrasseKaufenUIVorschau
{
	public static void main(String[] args)
	{
		Spielansicht.zeige(new StrasseKaufenUI(StrasseTest.getDefaultStrasse(), SpielerImplTest.getDefaultSpieler(),
				Spielansicht.getSpielfeldAnsicht()));
	}
}
