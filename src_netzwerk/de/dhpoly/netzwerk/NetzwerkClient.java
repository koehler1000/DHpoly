package de.dhpoly.netzwerk;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import de.dhpoly.datenobjekt.Datenobjekt;

public interface NetzwerkClient
{
	/**
	 * Sendet @param text an den Server
	 */
	void sendeAnServer(String text) throws IOException;

	/**
	 * Sendet @param obj an den Server
	 */
	public void sendeAnServer(Datenobjekt obj);
	
	/**
	 * Baut eine Verbindung mit dem Server mit der @param ip und dem @param port auf.
	 */
	void verbinden(String ip, int port) throws ConnectException, UnknownHostException, IOException;

	@Deprecated
	void setDatenobjektverwalter(Datenobjektverwalter verwalter);
	
	@Deprecated
	String read();
	
	/**
	 * Trennt die Verbindung zum Server
	 */
	boolean verbindungTrennen();

	void sendQuitMessage();

}
