package de.dhpoly.spieler.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import de.dhpoly.einstellungen.control.EinstellungenImpl;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.feld.control.FelderverwaltungImpl;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.feld.control.StrasseTest;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImpl;

public class StrassenbesitzeUITest
{

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("StrassenbesitzeUITest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);

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

		frame.add(new StrassenbesitzeUI(felderverwaltung, spieler));

		frame.setVisible(true);
	}
}
