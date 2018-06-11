package de.dhpoly.spiel.control;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.empfaenger.model.Empfaenger;
import de.dhpoly.logik.Logik;
import de.dhpoly.nachricht.model.Nachricht;
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
			Nachricht nachricht = new Nachricht("Aktuelle Spieleranzahl: " + spiel.getSpieler().size(), Empfaenger.ALLE_SPIELER);
			spiel.empfange(nachricht);
		}
	}

}
