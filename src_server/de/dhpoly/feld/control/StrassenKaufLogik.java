package de.dhpoly.feld.control;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.model.StrasseKaufen;
import de.dhpoly.logik.Logik;
import de.dhpoly.spiel.Spiel;

public class StrassenKaufLogik implements Logik
{

	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel)
	{
		if (objekt instanceof StrasseKaufen)
		{
			StrasseKaufen strasseKaufen = (StrasseKaufen) objekt;
			spiel.kaufe(strasseKaufen, strasseKaufen.getSender());
		}
	}

}
