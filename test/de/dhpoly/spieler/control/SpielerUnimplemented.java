package de.dhpoly.spieler.control;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.model.SpielerDaten;
import de.dhpoly.spieler.model.SpielerTyp;

public class SpielerUnimplemented implements Spieler
{
	@Override
	public SpielerDaten getDaten()
	{
		return new SpielerDaten(SpielerTyp.LOKAL, "Typ von 'Unimplemented'");
	}

	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel)
	{}
}
