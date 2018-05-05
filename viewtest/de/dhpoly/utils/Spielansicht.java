package de.dhpoly.utils;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.model.SpielerTyp;

public class Spielansicht
{
	private Spielansicht()
	{}

	public static SpielfeldAnsicht getSpielfeldAnsicht()
	{
		Spieler spieler = new Spieler(SpielerTyp.LOKAL, "foo");
		return new SpielfeldAnsicht(spieler, null);
	}

	public static void zeige(Oberflaeche oberflaeche)
	{
		Fenster fenster = new Fenster(new Bilderverwalter());
		fenster.zeigeComponente(oberflaeche, "Oberfläche");
	}
}
