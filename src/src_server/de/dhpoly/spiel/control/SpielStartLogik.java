package de.dhpoly.spiel.control;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.logik.Logik;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.model.SpielStart;
import de.dhpoly.spiel.model.SpielStatus;

public class SpielStartLogik implements Logik
{
	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel)
	{
		if (objekt instanceof SpielStart && spiel.getStatus() == SpielStatus.SPIEL_VORBEREITUNG)
		{
			spiel.starteSpiel();
		}
	}
}
