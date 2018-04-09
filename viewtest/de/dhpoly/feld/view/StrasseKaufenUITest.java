package de.dhpoly.feld.view;

import de.dhpoly.feld.control.StrasseTest;
import de.dhpoly.spieler.control.SpielerImplTest;

public class StrasseKaufenUITest // NOSONAR
{
	public static void main(String[] args)
	{
		new StrasseKaufenUI(StrasseTest.getDefaultStrasse(), SpielerImplTest.getDefaultSpieler(), null);
	}
}
