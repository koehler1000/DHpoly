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
import observerpattern.Beobachter;

public interface Spiel
{
	Spieler getAktuellerSpieler();

	List<Spieler> getSpieler();

	List<Feld> getFelder();

	double getFaktorMiete();

	void naechsterSpieler();

	void ruecke();

	void verarbeiteKarte(Karte karte);

	void fuegeSpielerHinzu(Spieler spieler);

	void fuegeLokalenSpielerHinzu(String spielerName);

	void fuegeComputerSpielerHinzu(String text);

	void naechsterSchritt();

	String getBeschreibungNaechsterSchritt();

	void addBeobachter(Beobachter beobachter);

	void verarbeiteFehler(Fehler fehler);

	SpielStatus getStatus();

	void starteSpiel();

	void setWuerfelPaar(Wuerfelpaar wuerfelPaar);

	void setFelder(List<Feld> felder);

	void setFenster(Fenster fenster);

	boolean isAnimationen();

	void setWetter(Wetter wetter);

	void setEinstellungen(Einstellungen einstellungen);

	List<Feld> getFelder(Spieler spieler);

	void setAnimationen(boolean b);
}
