package de.dhpoly.spieler;

import java.util.List;
import java.util.Optional;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.karte.Karte;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.wuerfel.control.Wuerfel;
import observerpattern.Beobachter;

public interface Spieler
{
	void einzahlen(RessourcenDatensatz datensatz);

	void einzahlen(List<RessourcenDatensatz> datensaetze);

	void auszahlen(RessourcenDatensatz datensatz);

	void auszahlen(List<RessourcenDatensatz> datensaetze);

	boolean isNegative();

	int getFeldNr();

	String getName();

	void setFeldNr(int feldNrSoll);

	void zeigeKaufmoeglichkeit(Strasse strasse);

	void verarbeiteKarte(Karte karte);

	int getSpielerNr();

	void setAktuellerSpieler(boolean isAktuell);

	boolean isAktuellerSpieler();

	void addBeobachterHinzu(Beobachter beobachter);

	List<RessourcenDatensatz> getRessourcenKontoauszug();

	int getRessourcenWerte(Ressource ressource);

	boolean kannBezahlen(List<RessourcenDatensatz> kosten);

	void ausscheiden();

	void gewonnen();

	List<Feld> getFelder();

	void setSpielerNr(int nr);

	boolean hatVerloren();

	void setSpielfeldAnsicht(SpielfeldAnsicht ansicht);

	void zeigeDatenobjekt(Datenobjekt objekt);

	void vergebeRessourcen(int ertrag);

	void setWuerfelnMoeglich(boolean value);

	void setWuerfelWeitergabeMoeglich(boolean value);

	void setSpielfeldAnsichtDaten(Optional<Fenster> fenster, List<Wuerfel> wuerfel);
}
