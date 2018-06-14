package de.dhpoly.netzwerk;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.spieler.model.Spieler;

public interface NetzwerkServer
{
	String getIp() throws UnknownHostException;

	void empfange(Datenobjekt objekt);

	void run(String[] args) throws IOException;

	void setDatenobjektverwalter(Datenobjektverwalter verwalter);

	/**
	 * Sendet @param obj an den @param spieler
	 */
	public void sendeAnSpieler(Datenobjekt obj, Spieler spieler);

	/**
	 * Sendet @param obj an die Empfänger @param spieler
	 */
	public void sendeAnSpieler(Datenobjekt obj, List<Spieler> spieler);

	/**
	 * Sendet @param obj an alle Spieler
	 */
	public void sendeAnSpieler(Datenobjekt obj);
}
