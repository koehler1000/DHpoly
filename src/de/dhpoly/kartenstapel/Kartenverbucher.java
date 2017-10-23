package de.dhpoly.kartenstapel;

import java.util.List;

import de.dhpoly.kartenstapel.model.Karte;
import de.dhpoly.spieler.Geldhaber;

public interface Kartenverbucher
{
	public void bewegeGeld(Karte karte, List<Geldhaber> spieler, Geldhaber ziehenderSpieler, Geldhaber freiparken);
}
