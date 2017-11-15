package de.dhpoly.spieler;

import de.dhpoly.feld.control.Strasse;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.karte.Karte;
import observerpattern.Beobachter;

public interface Spieler
{
	int getBargeld();

	void einzahlen(int betrag);

	void auszahlen(int betrag);

	void ueberweiseGeld(int betrag, Spieler empfaenger);

	boolean isNegative();

	int getFeldNr();

	String getName();

	void setFeldNr(int feldNrSoll);

	void zeigeTransaktionsvorschlag(Transaktion transaktion);

	void zeigeKaufmoeglichkeit(Strasse strasse);

	void zeigeKarte(Karte karte);

	int getSteinVorrat();

	int getHolzVorrat();

	int getSpielerNr();

	void setAkutellerSpieler(boolean isAktuell);

	boolean isAktuellerSpieler();

	void addBeobachterHinzu(Beobachter beobachter);
}
