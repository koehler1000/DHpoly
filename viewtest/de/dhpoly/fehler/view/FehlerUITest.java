package de.dhpoly.fehler.view;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.fehler.model.Fehler;
import de.dhpoly.oberflaeche.view.Fenster;

public class FehlerUITest // NOSONAR
{
	public static void main(String[] args)
	{
		Fenster fenster = new Fenster(new Bilderverwalter());
		Fehler fehler = new Fehler("Das ist der Fehlertext", null);
		fenster.zeigeComponente(new FehlerUI(fehler, null));
	}
}
