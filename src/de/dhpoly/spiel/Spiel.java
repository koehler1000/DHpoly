package de.dhpoly.spiel;

import de.dhpoly.karte.Karte;
import de.dhpoly.spieler.Spieler;

public interface Spiel
{

	Spieler getAktuellerSpieler();

	void naechsterSpieler();

	void ruecke(Spieler spieler, int augensumme);

	void ruecke(Spieler spieler, int augenzahl1, int augenzahl2);

	void verarbeiteKarte(Karte karte);

}
