package de.dhpoly.spielfeld.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.StrasseTest;

public class SpielfeldUITest
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Test");

		List<Feld> felder = new ArrayList<>();

		for (int i = 0; i < 40; i++)
		{
			felder.add(StrasseTest.getDefaultStrasse("Test " + i));
		}

		frame.add(new SpielfeldUI(felder));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 150);
		frame.setVisible(true);
	}
}