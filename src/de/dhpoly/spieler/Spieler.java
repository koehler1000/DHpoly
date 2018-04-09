package de.dhpoly.spieler;

import java.util.List;
import java.util.Optional;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.karte.Karte;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.model.Ressource;
import observerpattern.Beobachter;

public interface Spieler
{
	void einzahlen(RessourcenDatensatz datensatz);

	void einzahlen(List<RessourcenDatensatz> datensaetze);

	void auszahlen(RessourcenDatensatz datensatz);

	void auszahlen(List<RessourcenDatensatz> datensaetze);

	void ueberweise(RessourcenDatensatz datensatz, Spieler empfaenger);

	boolean isNegative();

	int getFeldNr();

	String getName();

	void setFeldNr(int feldNrSoll);

	void zeigeTransaktionsvorschlag(Transaktion transaktion);

	void zeigeKaufmoeglichkeit(Strasse strasse);

	void zeigeKarte(Karte karte);

	int getSpielerNr();

	void setAkutellerSpieler(boolean isAktuell);

	boolean isAktuellerSpieler();

	void addBeobachterHinzu(Beobachter beobachter);

	List<RessourcenDatensatz> getRessourcenTransaktionen();

	int getRessourcenWerte(Ressource ressource);

	boolean kannBezahlen(List<RessourcenDatensatz> kosten);

	void ausscheiden();

	void gewonnen();

	List<Feld> getFelder();

	void feldHinzu(Feld feld);

	void feldWeg(Feld feld);

	void setSpielerNr(int nr);

	boolean hatVerloren();

	void setSpielfeldAnsicht(SpielfeldAnsicht ansicht);

	@Deprecated // stattdessen zeigeXY(Datentyp) verwenden
	Optional<SpielfeldAnsicht> getUI();

	void zeigeTransaktionErfolgreich(Transaktion transaktion);

	void zeigeTransaktionGescheitert(Transaktion transaktion);

	@Deprecated
	void informiereErfolgreicheTransaktion(Transaktion transaktion);

	void sperreOberflaeche(Transaktion transaktion);
}
