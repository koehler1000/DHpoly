package de.dhpoly.kartenverbucher;

import java.util.List;

import de.dhpoly.karte.model.BezahlKarte;
import de.dhpoly.karte.model.RueckenKarte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.spieler.Spieler;

public interface Kartenverbucher
{
	void bewegeGeld(BezahlKarte karte, List<Spieler> spieler, Spieler ziehenderSpieler);

	void bewegeSpieler(RueckenKarte karte, Spieler spieler, Wetter wetter);
}
