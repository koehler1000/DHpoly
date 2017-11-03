package de.dhpoly.feld;

import de.dhpoly.spieler.Spieler;

public interface Feld
{
	public String getBeschriftung();

	public void betreteFeld(Spieler spieler, int augensumme);
}
