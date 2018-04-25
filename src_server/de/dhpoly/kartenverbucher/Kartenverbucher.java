package de.dhpoly.kartenverbucher;

import java.util.List;

import de.dhpoly.karte.model.BezahlKarte;
import de.dhpoly.karte.model.RueckenKarte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.logik.Logik;
import de.dhpoly.spieler.Spieler;

public interface Kartenverbucher extends Logik
{
	void bewegeGeld(BezahlKarte karte, List<Spieler> spieler, Spieler ziehenderSpieler);

	void bewegeSpieler(RueckenKarte karte, Spieler spieler, Wetter wetter);
}
