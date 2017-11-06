package de.dhpoly.feld;

import de.dhpoly.karte.model.Wetter;
import de.dhpoly.spieler.Spieler;

public interface Feld
{
	public String getBeschriftung();

	public void betreteFeld(Spieler spieler, int augensumme, Wetter aktuellesWetter);
}
