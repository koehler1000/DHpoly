package de.dhpoly.netzwerk;

import de.dhpoly.datenobjekt.Datenobjekt;

/**
 * @deprecated Ab jetzt NetzwerkClient verwenden
 */

@Deprecated
public interface Client extends NetzwerkTeilnehmer
{
	/**
	 * Sendet @param obj an den Server
	 */
	public void sendeAnServer(Datenobjekt obj);
}
