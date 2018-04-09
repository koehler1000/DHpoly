package de.dhpoly.feld.view;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.einstellungen.model.EinstellungenImpl;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.oberflaeche.view.Fenster;

public class StrasseInfoUITest // NOSONAR
{
	public static void main(String[] args)
	{
		new Fenster(new Bilderverwalter()).zeigeComponente(new StrasseInfoUI(new Strasse(null, 1000,
				new int[] { 10, 30, 40, 80, 100 }, new EinstellungenImpl().getHauskosten(1), 1, "Teststrasse"), null));
	}
}
