package de.dhpoly.utils;

import java.util.ArrayList;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.einstellungen.model.EinstellungenImpl;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielImpl;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImpl;
import de.dhpoly.wuerfel.control.WuerfelpaarImpl;

public class Spielansicht
{
	private Spielansicht()
	{}

	public static SpielfeldAnsicht getSpielfeldAnsicht()
	{
		Spiel spiel = new SpielImpl(new ArrayList<>(), new EinstellungenImpl(), new WuerfelpaarImpl());
		Spieler spieler = new SpielerImpl("foo", new EinstellungenImpl(), spiel);
		return new SpielfeldAnsicht(spiel, spieler);
	}

	public static void zeige(Oberflaeche oberflaeche)
	{
		Fenster fenster = new Fenster(new Bilderverwalter());
		fenster.zeigeComponente(oberflaeche);
	}
}
