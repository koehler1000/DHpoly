package de.dhpoly.kartenverbucher;

import java.util.List;

import de.dhpoly.karte.control.BezahlKarte;
import de.dhpoly.karte.control.RueckenKarte;
import de.dhpoly.spieler.Spieler;

public interface Kartenverbucher
{
	public void bewegeGeld(BezahlKarte karte, List<Spieler> spieler, Spieler ziehenderSpieler);

	public void bewegeSpieler(RueckenKarte karte, Spieler spieler);
}
