package de.dhpoly.nachricht.control;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.nachricht.NachrichtLogik;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.spiel.Spiel;

public class NachrichtLogikImpl implements NachrichtLogik
{
	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel)
	{
		if (objekt instanceof Nachricht)
		{
			sendeNachricht((Nachricht) objekt, spiel);
		}
	}

	@Override
	public void sendeNachricht(Nachricht nachricht, Spiel spiel)
	{
		System.out.println("Server empfangen: " + nachricht.getNachricht());

	}
}
