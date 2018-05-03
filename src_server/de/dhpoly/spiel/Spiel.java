package de.dhpoly.spiel;

import java.util.List;
import java.util.Optional;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.fehler.model.Fehler;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.model.StrasseKaufen;
import de.dhpoly.karte.model.Karte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.logik.Logikverwalter;
import de.dhpoly.spiel.model.SpielStatus;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.model.SpielerDaten;
import de.dhpoly.wuerfel.Wuerfelpaar;

public interface Spiel extends Logikverwalter
{
	Spieler getAktuellerSpieler();

	List<Spieler> getSpieler();

	List<Feld> getFelder();

	double getFaktorMiete();

	void ruecke();

	void verarbeiteKarte(Karte karte);

	void fuegeSpielerHinzu(Spieler spieler);

	void fuegeLokalenSpielerHinzu(String spielerName);

	void fuegeComputerSpielerHinzu(String text);

	void wuerfeln(Spieler spieler);

	void wuerfelWeitergeben(Spieler spieler);

	void verarbeiteFehler(Fehler fehler);

	SpielStatus getStatus();

	void starteSpiel();

	void setWuerfelPaar(Wuerfelpaar wuerfelPaar);

	void setFelder(List<Feld> felder);

	void setWetter(Wetter wetter);

	void setEinstellungen(Einstellungen einstellungen);

	List<Feld> getFelder(Spieler spieler);

	boolean kannWuerfeln(Spieler spieler);

	boolean kannWuerfelWeitergeben(Spieler spieler);

	void kaufe(StrasseKaufen strasse, Spieler spieler);

	Wetter getWetter();

	SpielerDaten getAktuellerSpielerDaten();

	Optional<Spieler> getSpieler(SpielerDaten spielerDaten);

	Optional<Spieler> getSpieler(Optional<SpielerDaten> eigentuemer);
}
