package de.dhpoly.feld.view;

import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.feld.model.StrasseKaufen;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.utils.Spielansicht;

public class StrasseKaufenUIVorschau
{
	public static void main(String[] args)
	{
		Spielansicht.zeige(
				new StrasseKaufenUI(new StrasseKaufen(new StrasseDaten(), new Spieler("Me")),
						Spielansicht.getSpielfeldAnsicht()));
	}
}
