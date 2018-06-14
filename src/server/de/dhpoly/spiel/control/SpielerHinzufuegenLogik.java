package de.dhpoly.spiel.control;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.logik.Logik;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.model.Spieler;

public class SpielerHinzufuegenLogik implements Logik
{
	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel)
	{
		if (objekt instanceof Spieler)
		{
			spiel.fuegeSpielerHinzu((Spieler) objekt);
		}
	}
}
