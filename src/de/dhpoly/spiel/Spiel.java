package de.dhpoly.spiel;

import java.util.List;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.fehler.model.Fehler;
import de.dhpoly.feld.Feld;
import de.dhpoly.karte.Karte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.spiel.model.SpielStatus;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.wuerfel.Wuerfelpaar;
import de.dhpoly.wuerfel.control.Wuerfel;
import observerpattern.Beobachter;

public interface Spiel
{
	Spieler getAktuellerSpieler();

	Einstellungen getEinstellungen();

	Wetter getWetter();

	List<Wuerfel> getWuerfel();

	List<Spieler> getSpieler();

	List<Feld> getFelder();

	double getFaktorMiete();

	void naechsterSpieler();

	void ruecke();

	void verarbeiteKarte(Karte karte);

	void fuegeSpielerHinzu(Spieler spieler);

	void fuegeSpielerHinzu(String spielerName);

	void naechsterSchritt();

	String getBeschreibungNaechsterSchritt();

	void addBeobachter(Beobachter beobachter);

	void verarbeiteFehler(Fehler fehler);

	SpielStatus getStatus();

	void setStatus(SpielStatus spielLaeuft);

	Wuerfelpaar getWuerfelPaar();

	void setWuerfelPaar(Wuerfelpaar wuerfelPaar);

	Fenster getFenster();

	void setFelder(List<Feld> felder);

	void setFenster(Fenster fenster);

	boolean isAnimationen();

	void setSpieler(List<Spieler> spieler);

	void setWetter(Wetter wetter);

	void setEinstellungen(Einstellungen einstellungen);
}
