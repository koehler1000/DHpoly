package de.dhpoly.spieler.view;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.einstellungen.model.EinstellungenImpl;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.feld.control.FelderverwaltungImpl;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.feld.control.StrasseTest;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImpl;
import de.dhpoly.utils.Spielansicht;

public class StrassenbesitzeUITest // NOSONAR
{
	public static void main(String[] args)
	{
		Felderverwaltung felderverwaltung = new FelderverwaltungImpl();
		Spieler spieler = new SpielerImpl("Bla", new EinstellungenImpl(), null);

		List<Feld> felder = new ArrayList<>();

		for (int i = 0; i < 5; i++)
		{
			Strasse str = StrasseTest.getDefaultStrasse("Str" + i);
			felder.add(str);
			str.kaufe(spieler);
		}

		felderverwaltung.setFelder(felder);

		Spielansicht.zeige(new StrassenbesitzeUI(felderverwaltung, spieler, Spielansicht.getSpielfeldAnsicht()));
	}
}
