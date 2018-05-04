package de.dhpoly.nachricht.control;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.logik.Logik;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.spiel.Spiel;

public class NachrichtVerwalter implements Logik
{
	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel)
	{
		Nachricht nachricht = (Nachricht) objekt;
		System.out.println("Server empfangen: " + nachricht.getNachricht());
	}
}
