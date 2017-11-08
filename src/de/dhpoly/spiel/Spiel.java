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

	Einstellungen getEinstellungen();

	Wetter getWetter();

	Wuerfel getWuerfel();

	List<Spieler> getSpieler();

	List<Feld> getFelder();

	int getFaktorMiete();

	void naechsterSpieler();

	void ruecke();

	void verarbeiteKarte(Karte karte);

	void fuegeSpielerHinzu(Spieler spieler);
}
