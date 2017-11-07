package de.dhpoly.spiel;

import java.util.List;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.feld.Feld;
import de.dhpoly.karte.Karte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.wuerfel.Wuerfel;

public interface Spiel
{
	Spieler getAktuellerSpieler();

	void naechsterSpieler();

	void ruecke();

	void ruecke(Spieler spieler, int augensumme);

	void ruecke(Spieler spieler, int augenzahl1, int augenzahl2);

	void verarbeiteKarte(Karte karte);

	Einstellungen getEinstellungen();

	Wetter getWetter();

	List<Spieler> getSpieler();

	List<Feld> getFelder();

	int getFaktorMiete();

	void fuegeSpielerHinzu(Spieler spieler);

	Wuerfel getWuerfel();
}
