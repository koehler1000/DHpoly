package de.dhpoly.netzwerk;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.spiel.Spiel;

public interface NetzwerkServer
{
	void sende(Datenobjekt obj);

	void sende(String string);

	void addInteressent(Spiel spiel);
}
