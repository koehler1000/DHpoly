package de.dhpoly.netzwerk;

import java.io.IOException;
import java.net.UnknownHostException;

import de.dhpoly.datenobjekt.Datenobjekt;

public interface NetzwerkServer
{
	void sendeAnClients(Datenobjekt obj) throws IOException;

	void sendeAnClients(String string) throws IOException;

	String getIp() throws UnknownHostException;

	void verbindungAbbauen() throws IOException;

	void empfange(Datenobjekt objekt);
}
