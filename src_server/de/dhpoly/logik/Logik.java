package de.dhpoly.logik;

import java.io.IOException;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.spiel.Spiel;

public interface Logik
{
	void verarbeite(Datenobjekt objekt, Spiel spiel) throws IOException;
}
