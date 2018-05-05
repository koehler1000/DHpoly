package de.dhpoly.feld.view;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.model.Bewegung;
import de.dhpoly.logik.Logik;
import de.dhpoly.spiel.Spiel;

public class BewegungLogik implements Logik
{
	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel)
	{
		if (objekt instanceof Bewegung)
		{
			Bewegung bewegung = (Bewegung) objekt;
			// TODO bewegen
		}
	}
}
