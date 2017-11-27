package de.dhpoly.feld.view;

import javax.swing.JFrame;

import de.dhpoly.einstellungen.model.EinstellungenImpl;
import de.dhpoly.feld.control.Strasse;

public class StrasseInfoUITest
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("StrasseInfoUITest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(100, 200);

		frame.add(new StrasseInfoUI(new Strasse(null, 1000, new int[] { 10, 30, 40, 80, 100 },
				new EinstellungenImpl().getHauskosten(1), 1, "Teststrasse")));

		frame.setVisible(true);
	}
}
