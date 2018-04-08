package de.dhpoly.feld.view;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.einstellungen.model.EinstellungenImpl;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.oberflaeche.view.OberflaecheUI;

public class StrasseInfoUITest // NOSONAR
{
	public static void main(String[] args)
	{
		new OberflaecheUI(new Bilderverwalter(), StrasseInfoUITest.class.getName())
				.zeigeKomplettesFenster(new StrasseInfoUI(new Strasse(null, 1000, new int[] { 10, 30, 40, 80, 100 },
						new EinstellungenImpl().getHauskosten(1), 1, "Teststrasse")));
	}
}
