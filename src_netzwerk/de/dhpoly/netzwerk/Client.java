package de.dhpoly.netzwerk;

import de.dhpoly.datenobjekt.Datenobjekt;

public interface Client extends NetzwerkTeilnehmer
{
	/**
	 * Sendet @param obj an den Server
	 */
	public void sendeAnServer(Datenobjekt obj);
}
