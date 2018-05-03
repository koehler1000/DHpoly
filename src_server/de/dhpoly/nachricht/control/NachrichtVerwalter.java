package de.dhpoly.nachricht.control;

import java.io.IOException;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.logik.Logik;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.spiel.Spiel;

public class NachrichtVerwalter implements Logik
{
	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel) throws IOException
	{
		Nachricht nachricht = (Nachricht) objekt;
		System.out.println(nachricht.getNachricht());
	}
}
