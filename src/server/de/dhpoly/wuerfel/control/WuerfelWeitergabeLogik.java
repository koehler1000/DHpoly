package de.dhpoly.wuerfel.control;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.logik.Logik;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.wuerfel.model.WuerfelWeitergabe;

public class WuerfelWeitergabeLogik implements Logik
{
	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel)
	{
		if (objekt instanceof WuerfelWeitergabe)
		{
			WuerfelWeitergabe weitergabe = (WuerfelWeitergabe) objekt;
			spiel.wuerfelWeitergeben(weitergabe.getSpieler());
		}
	}
}
