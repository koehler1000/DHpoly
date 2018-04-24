package de.dhpoly.fehler.view;

import de.dhpoly.spielobjekte.fehler.model.Fehler;
import de.dhpoly.spielobjekte.fehler.model.FehlerTyp;
import de.dhpoly.utils.Spielansicht;

public class FehlerUIVorschau
{
	public static void main(String[] args)
	{
		Fehler fehler = new Fehler("Das ist der Fehlertext", FehlerTyp.FEHLER_SPIELER);
		Spielansicht.zeige(new FehlerUI(fehler, Spielansicht.getSpielfeldAnsicht()));
	}
}
