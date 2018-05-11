package de.dhpoly.netzwerk;

import java.io.IOException;

import de.dhpoly.datenobjekt.Datenobjekt;

public interface NetzwerkClient
{
	void sendeAnServer(String text) throws IOException;

	/**
	 * Sendet @param obj an den Server
	 */
	public void sendeAnServer(Datenobjekt obj);

	void setDatenobjektverwalter(Datenobjektverwalter verwalter);

	String read();

	void sendQuitMessage();

}
