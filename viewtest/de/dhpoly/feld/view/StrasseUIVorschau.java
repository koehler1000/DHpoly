package de.dhpoly.feld.view;

import de.dhpoly.feld.control.FeldStrasse;
import de.dhpoly.feld.control.FeldStrasseTest;
import de.dhpoly.spieler.control.SpielerImplTest;
import de.dhpoly.utils.Spielansicht;

public class StrasseUIVorschau
{
	public static void main(String[] args)
	{
		FeldStrasse strasse = FeldStrasseTest.getDefaultStrasse();
		Spielansicht.zeige(
				new StrasseKaufenUI(strasse, SpielerImplTest.getDefaultSpieler(), Spielansicht.getSpielfeldAnsicht()));
	}
}
