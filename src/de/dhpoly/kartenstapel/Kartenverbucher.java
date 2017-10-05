package de.dhpoly.kartenstapel;

import java.util.List;

import de.monopoly.kartenstapel.model.Karte;
import de.monopoly.spieler.Geldhaber;

public interface Kartenverbucher
{
	public void bewegeGeld(Karte karte, List<Geldhaber> spieler, Geldhaber ziehenderSpieler,
			Geldhaber freiparken);
}
