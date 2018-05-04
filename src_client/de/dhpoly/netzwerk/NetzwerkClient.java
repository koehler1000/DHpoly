package de.dhpoly.netzwerk;

import java.io.IOException;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public interface NetzwerkClient
{
	void sendeAnServer(String text) throws IOException;

	void sendeAnServer(Datenobjekt objekt) throws IOException;

	void addAnsicht(SpielfeldAnsicht ansicht);

	void verbindungAufbauen() throws IOException;

	void verbindungAbbauen() throws IOException;

	void empfange(Datenobjekt obj);
}
