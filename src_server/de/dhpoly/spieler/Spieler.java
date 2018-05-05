package de.dhpoly.spieler;

import de.dhpoly.feld.model.StrasseKaufen;
import de.dhpoly.logik.Logik;
import de.dhpoly.spieler.model.SpielerDaten;

public interface Spieler extends Logik
{
	void setWuerfelnMoeglich(boolean value);

	void setWuerfelWeitergabeMoeglich(boolean value);

	void kaufe(StrasseKaufen strasse);

	SpielerDaten getDaten();
}
