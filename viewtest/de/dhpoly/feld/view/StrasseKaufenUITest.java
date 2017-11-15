package de.dhpoly.feld.view;

import javax.swing.JFrame;

import de.dhpoly.feld.control.StrasseTest;
import de.dhpoly.spieler.control.SpielerImplTest;

public class StrasseKaufenUITest
{
	public static void main(String[] args)
	{
		StrasseKaufenUI strasseKaufenUI = new StrasseKaufenUI(StrasseTest.getDefaultStrasse(),
				SpielerImplTest.getDefaultSpieler());

		strasseKaufenUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
