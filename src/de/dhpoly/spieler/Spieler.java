package de.dhpoly.spieler;

import java.util.List;
import java.util.Set;

import de.dhpoly.feld.control.Strasse;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.karte.Karte;
import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.model.Ressource;
import observerpattern.Beobachter;

public interface Spieler
{
	int getBargeld();

	void einzahlen(int betrag);

	void einzahlen(RessourcenDatensatz datensatz);

	void auszahlen(int betrag);

	void auszahlen(RessourcenDatensatz datensatz);

	void ueberweiseGeld(int betrag, Spieler empfaenger);

	void ueberweiseGeld(RessourcenDatensatz datensatz, Spieler empfaenger);

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

	Set<RessourcenDatensatz> getAktuelleVorraete();

	List<RessourcenDatensatz> getRessourcenTransaktionen();

	int getRessourcenWerte(Ressource ressource);
}
