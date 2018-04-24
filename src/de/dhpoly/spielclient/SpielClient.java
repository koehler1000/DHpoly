package de.dhpoly.spielclient;

import de.dhpoly.datenobjekt.Datenobjekt;

public interface SpielClient
{
	void empfange(String text);

	void empfange(Datenobjekt objekt);
}
