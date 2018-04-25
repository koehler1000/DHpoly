package de.dhpoly.netzwerk;

import java.io.IOException;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.spiel.Spiel;

public interface NetzwerkServer
{
	void sende(Datenobjekt obj) throws IOException;

	void sende(String string) throws IOException;

	void addInteressent(Spiel spiel);
}
