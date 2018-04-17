package de.dhpoly.utils;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielImplTest;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerLokal;
import de.dhpoly.wuerfel.control.WuerfelpaarImpl;

public class Spielansicht
{
	private Spielansicht()
	{}

	public static SpielfeldAnsicht getSpielfeldAnsicht()
	{
		Spiel spiel = SpielImplTest.getDefaultSpiel();
		Spieler spieler = new SpielerLokal("foo", spiel);
		return new SpielfeldAnsicht(spiel, new WuerfelpaarImpl().getWuerfel(), spieler);
	}

	public static void zeige(Oberflaeche oberflaeche)
	{
		Fenster fenster = new Fenster(new Bilderverwalter());
		fenster.zeigeComponente(oberflaeche, "Oberfläche");
	}
}
