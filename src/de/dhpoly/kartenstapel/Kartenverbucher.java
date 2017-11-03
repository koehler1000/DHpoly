package de.dhpoly.kartenstapel;

import java.util.List;

import de.dhpoly.karte.control.BezahlKarte;
import de.dhpoly.spieler.Spieler;

public interface Kartenverbucher
{
	public void bewegeGeld(BezahlKarte karte, List<Spieler> spieler, Spieler ziehenderSpieler, Spieler freiparken);
}
