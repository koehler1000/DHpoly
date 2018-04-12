package de.dhpoly.feld.view;

import de.dhpoly.feld.control.Strasse;
import de.dhpoly.feld.control.StrasseTest;
import de.dhpoly.spieler.control.SpielerImplTest;
import de.dhpoly.utils.Spielansicht;

public class StrasseUIVorschau
{
	public static void main(String[] args)
	{
		Strasse strasse = StrasseTest.getDefaultStrasse();
		Spielansicht.zeige(
				new StrasseKaufenUI(strasse, SpielerImplTest.getDefaultSpieler(), Spielansicht.getSpielfeldAnsicht()));
	}
}
