package de.dhpoly.netzwerk;

import java.io.IOException;
import java.net.UnknownHostException;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.spiel.Spiel;

public interface NetzwerkServer
{
	void sende(Datenobjekt obj) throws IOException;

	void sende(String string) throws IOException;

	void addInteressent(Spiel spiel);

	String getIp() throws UnknownHostException;

	void verbindungAbbauen() throws IOException;
}
