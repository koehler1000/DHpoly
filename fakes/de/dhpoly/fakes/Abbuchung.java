package de.dhpoly.fakes;

import javax.swing.JOptionPane;

public class Abbuchung
{
	public static void main(String[] args)
	{
		JOptionPane.showMessageDialog(null, "Alter Kontostand:    50.000\n"
				+ "Kauf:                          -10.000\n---\nNeuer Kontostand:  40.000", "Kontoauszug",
				JOptionPane.INFORMATION_MESSAGE);
	}

}
