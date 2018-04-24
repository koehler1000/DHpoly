package de.dhpoly.feld.view;

import de.dhpoly.feld.model.Strasse;
import de.dhpoly.utils.Spielansicht;

public class StrasseInfoUIVorschau
{
	public static void main(String[] args)
	{
		Spielansicht.zeige(new StrasseInfoUI(new Strasse(), Spielansicht.getSpielfeldAnsicht()));
	}
}
