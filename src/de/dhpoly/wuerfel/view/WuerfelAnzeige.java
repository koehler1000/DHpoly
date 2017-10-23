package de.dhpoly.wuerfel.view;

import javax.swing.JOptionPane;

public class WuerfelAnzeige
{
	public WuerfelAnzeige(int ergebnis1, int ergebnis2)
	{
		if (isInvalide(ergebnis1) || isInvalide(ergebnis2))
		{
			throw new IllegalArgumentException("Falsches W�rfelergebnis.");
		}

		// anzeigen
		JOptionPane.showMessageDialog(null, "W�rfelergebnis\n" + ergebnis1 + " und " + ergebnis2);
	}

	private boolean isInvalide(int ergebnis)
	{
		return ergebnis < 1 || ergebnis > 6;
	}
}
