package de.dhpoly.feld.view;

import de.dhpoly.einstellungen.model.EinstellungenImpl;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.utils.Spielansicht;

public class StrasseInfoUIVorschau
{
	public static void main(String[] args)
	{
		Spielansicht.zeige(new StrasseInfoUI(new Strasse(null, 1000, new int[] { 10, 30, 40, 80, 100 },
				new EinstellungenImpl().getHauskosten(1), 1, "Teststrasse"), Spielansicht.getSpielfeldAnsicht()));
	}
}
