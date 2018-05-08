package de.dhpoly.netzwerk;

import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.spieler.model.Spieler;

public interface Server extends NetzwerkTeilnehmer
{
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
