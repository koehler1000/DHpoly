package de.dhpoly.spielfeld.model;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.einstellungen.control.EinstellungenImpl;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.feld.control.FelderverwaltungImpl;
import de.dhpoly.feld.control.LosfeldTest;
import de.dhpoly.feld.control.StrasseTest;

public class Standardspielfeld
{
	public List<Feld> getStandardSpielfeld()
	{
		Einstellungen einstellungen = new EinstellungenImpl();

		Felderverwaltung verwaltung = new FelderverwaltungImpl();

		// List<Feld> standardfeld = new ArrayList<>();

		// for (int i = 0; i < 10; i++)
		// {
		// standardfeld.add(new Strasse(verwaltung, 1000, new int[] { 10, 20, 30 }, 1,
		// 1, "Test"));
		// standardfeld.add(new Losfeld(einstellungen));
		// standardfeld.add(new Ressourcenfeld(Ressource.HOLZ));
		// standardfeld.add(new Ressourcenfeld(Ressource.STEIN));
		// // standardfeld.add(new Ereignisfeld(kartenstapel));
		// }
		//
		List<Feld> standardfeld = new ArrayList<>();

		standardfeld.add(LosfeldTest.getDefaultFeld());

		for (int i = 0; i < 39; i++)
		{
			standardfeld.add(StrasseTest.getDefaultStrasse("Test " + i));
		}

		return standardfeld;
	}
}
