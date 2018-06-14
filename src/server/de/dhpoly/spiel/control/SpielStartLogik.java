package de.dhpoly.spiel.control;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.empfaenger.model.Empfaenger;
import de.dhpoly.logik.Logik;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.model.SpielStart;
import de.dhpoly.spiel.model.SpielStatus;

public class SpielStartLogik implements Logik
{
	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel)
	{
		if (objekt instanceof SpielStart && spiel.getStatus() == SpielStatus.SPIEL_VORBEREITUNG
				&& spiel.getSpieler().size() > 0)
		{
			spiel.starteSpiel();
			Nachricht nachricht = new Nachricht("Das DHPoly-Team wünscht viel Spaß", Empfaenger.ALLE_SPIELER);
			spiel.empfange(nachricht);
		}
	}
}
