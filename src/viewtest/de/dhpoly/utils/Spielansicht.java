package de.dhpoly.utils;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielUI;
import de.dhpoly.spieler.model.Spieler;

public class Spielansicht
{
	private Spielansicht()
	{}

	public static SpielUI getSpielfeldAnsicht()
	{
		Spieler spieler = new Spieler("foo");
		return new SpielUI(spieler, null);
	}

	public static void zeige(Oberflaeche oberflaeche)
	{
		Fenster fenster = new Fenster(new Bilderverwalter());
		fenster.zeigeComponente(oberflaeche, "Oberfläche");
	}
}
