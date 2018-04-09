package de.dhpoly.fehler.view;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.oberflaeche.view.Fenster;

public class FehlerUITest // NOSONAR
{
	public static void main(String[] args)
	{
		Fenster fenster = new Fenster(new Bilderverwalter());
		fenster.zeigeComponente(new FehlerUI("Das ist der Fehlertext", null));
	}
}
