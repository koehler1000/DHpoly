package de.dhpoly.einstellungen;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.logik.Logik;
import de.dhpoly.spiel.Spiel;

public class EinstellungenLogik implements Logik
{
	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel)
	{
		if (objekt instanceof Einstellungen)
		{
			spiel.setEinstellungen((Einstellungen) objekt);
		}
	}
}
