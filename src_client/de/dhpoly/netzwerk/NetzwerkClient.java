package de.dhpoly.netzwerk;

import java.io.IOException;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public interface NetzwerkClient
{
	void sende(String text) throws IOException;

	void sende(Datenobjekt objekt) throws IOException;

	void addAnsicht(SpielfeldAnsicht ansicht);

	void verbindungAufbauen() throws IOException;

	void verbindungAbbauen() throws IOException;

	void empfange(Datenobjekt obj);
}
