package de.dhpoly.netzwerk;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public interface NetzwerkClient
{
	void sende(String text);

	void sende(Datenobjekt objekt);

	void addAnsicht(SpielfeldAnsicht ansicht);
}
