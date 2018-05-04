package de.dhpoly.spieler;

import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.model.StrasseKaufen;
import de.dhpoly.karte.model.Karte;
import de.dhpoly.logik.Logik;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spieler.model.SpielerDaten;

public interface Spieler extends Logik
{
	void verarbeiteKarte(Karte karte);

	void gewonnen();

	List<Feld> getFelder();

	void setSpielerNr(int nr);

	boolean hatVerloren();

	void setSpielfeldAnsicht(SpielfeldAnsicht ansicht);

	void zeigeDatenobjekt(Datenobjekt objekt);

	void vergebeRessourcen(int ertrag);

	void setWuerfelnMoeglich(boolean value);

	void setWuerfelWeitergabeMoeglich(boolean value);

	void kaufe(StrasseKaufen strasse);

	SpielerDaten getDaten();
}
