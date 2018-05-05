package de.dhpoly.spieler;

import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.model.StrasseKaufen;
import de.dhpoly.logik.Logik;
import de.dhpoly.spieler.model.SpielerDaten;

public interface Spieler extends Logik
{
	List<Feld> getFelder();

	void vergebeRessourcen(int ertrag);

	void setWuerfelnMoeglich(boolean value);

	void setWuerfelWeitergabeMoeglich(boolean value);

	void kaufe(StrasseKaufen strasse);

	SpielerDaten getDaten();
}
