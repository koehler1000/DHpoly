package de.dhpoly.spielserver;

import de.dhpoly.datenobjekt.Datenobjekt;

public interface SpielServer
{
	void empfange(String text);

	void empfange(Datenobjekt objekt);
}
