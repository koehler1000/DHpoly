package de.dhpoly.spiel.control;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.logik.Logik;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.model.SpielStart;

public class SpielStarterLogik implements Logik
{
	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel)
	{
		if (objekt instanceof SpielStart)
		{
			spiel.starteSpiel();
		}
	}
}
