package de.dhpoly.fehler.view;

import de.dhpoly.fehler.model.Fehler;
import de.dhpoly.utils.Spielansicht;

public class FehlerUITest // NOSONAR
{
	public static void main(String[] args)
	{
		Fehler fehler = new Fehler("Das ist der Fehlertext", null);
		Spielansicht.zeige(new FehlerUI(fehler, Spielansicht.getSpielfeldAnsicht()));
	}
}
