package de.dhpoly.kartenstapel;

import java.util.List;

import de.dhpoly.kartenstapel.model.Karte;
import de.dhpoly.spieler.Spieler;

public interface Kartenverbucher
{
	public void bewegeGeld(Karte karte, List<Spieler> spieler, Spieler ziehenderSpieler, Spieler freiparken);
}
