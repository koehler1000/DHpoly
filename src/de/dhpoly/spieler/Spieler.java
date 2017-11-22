package de.dhpoly.spieler;

import java.util.List;

import de.dhpoly.feld.control.Strasse;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.karte.Karte;
import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.model.Ressource;
import observerpattern.Beobachter;

public interface Spieler
{
	@Deprecated
	int getBargeld();

	@Deprecated
	void einzahlen(int betrag);

	void einzahlen(RessourcenDatensatz datensatz);

	@Deprecated
	void auszahlen(int betrag);

	void auszahlen(RessourcenDatensatz datensatz);

	@Deprecated
	void ueberweiseGeld(int betrag, Spieler empfaenger);

	void ueberweise(RessourcenDatensatz datensatz, Spieler empfaenger);

	boolean isNegative();

	int getFeldNr();

	String getName();

	void setFeldNr(int feldNrSoll);

	void zeigeTransaktionsvorschlag(Transaktion transaktion);

	void zeigeKaufmoeglichkeit(Strasse strasse);

	void zeigeKarte(Karte karte);

	@Deprecated
	int getSteinVorrat();

	@Deprecated
	int getHolzVorrat();

	int getSpielerNr();

	void setAkutellerSpieler(boolean isAktuell);

	boolean isAktuellerSpieler();

	void addBeobachterHinzu(Beobachter beobachter);

	List<RessourcenDatensatz> getRessourcenTransaktionen();

	int getRessourcenWerte(Ressource ressource);
}
