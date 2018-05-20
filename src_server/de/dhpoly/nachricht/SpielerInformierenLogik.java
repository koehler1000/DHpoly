package de.dhpoly.nachricht;

import de.dhpoly.logik.Logik;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.spiel.Spiel;

public interface SpielerInformierenLogik extends Logik
{
	public void sendeNachricht(Nachricht nachricht, Spiel spiel);
}
