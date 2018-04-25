package de.dhpoly.logik;

import java.io.IOException;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;

public interface Logik
{
	void verarbeite(Datenobjekt objekt, Spiel spiel, Spieler spieler) throws IOException;
}
