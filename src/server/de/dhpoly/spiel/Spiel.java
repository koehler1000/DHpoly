package de.dhpoly.spiel;

import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.feld.model.FeldDaten;
import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.karte.model.Karte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.netzwerk.Datenobjektverwalter;
import de.dhpoly.spiel.model.SpielStatus;
import de.dhpoly.spiel.model.SpielfeldDaten;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.wuerfel.Wuerfelpaar;

public interface Spiel extends Datenobjektverwalter
{
	Spieler getAktuellerSpieler();

	List<Spieler> getSpieler();

	List<FeldDaten> getFelder();

	double getFaktorMiete();

	void ruecke();

	void verarbeiteKarte(Karte karte);

	void fuegeSpielerHinzu(Spieler spieler);

	void fuegeLokalenSpielerHinzu(String spielerName);

	void fuegeComputerSpielerHinzu(String text);

	void wuerfeln(Spieler spieler);

	void wuerfelWeitergeben(Spieler spieler);

	void verarbeiteFehler(Nachricht fehler);

	SpielStatus getStatus();

	void starteSpiel();

	void setWuerfelPaar(Wuerfelpaar wuerfelPaar);

	void setFelder(List<FeldDaten> felder);

	void setWetter(Wetter wetter);

	void setEinstellungen(Einstellungen einstellungen);

	boolean kannWuerfeln(Spieler spieler);

	boolean kannWuerfelWeitergeben(Spieler spieler);

	Wetter getWetter();

	void zeigeAllenSpielern(Datenobjekt objekt);

	void zeigeSpieler(Spieler sp, Datenobjekt objekt);

	SpielfeldDaten getSpielfeld();

	boolean kannSpielerStrasseKaufen(Spieler spieler, StrasseDaten strasse);

	void fuegeStrassenKaufHinzu(StrasseDaten strasse);

	void bewege(int anzahl, Spieler spieler);
}
