package de.dhpoly.feld.view;

import de.dhpoly.feld.model.Strasse;
import de.dhpoly.feld.model.StrasseKaufen;
import de.dhpoly.utils.Spielansicht;

public class StrasseKaufenUIVorschau
{
	public static void main(String[] args)
	{
		Spielansicht.zeige(new StrasseKaufenUI(new StrasseKaufen(new Strasse()), Spielansicht.getSpielfeldAnsicht()));
	}
}
