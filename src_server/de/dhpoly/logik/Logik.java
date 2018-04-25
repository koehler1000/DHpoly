package de.dhpoly.logik;

import java.io.IOException;

import de.dhpoly.datenobjekt.Datenobjekt;

public interface Logik
{
	void verarbeite(Datenobjekt objekt) throws IOException;
}
