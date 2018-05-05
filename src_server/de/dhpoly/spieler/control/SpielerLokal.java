package de.dhpoly.spieler.control;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.model.SpielerDaten;
import de.dhpoly.spieler.model.SpielerTyp;

public class SpielerLokal implements Spieler
{
	public SpielerLokal(String name, Spiel spiel)
	{
		this.daten = new SpielerDaten(SpielerTyp.COMPUTER, name);
	}

	SpielerDaten daten;

	@Override
	public SpielerDaten getDaten()
	{
		return daten;
	}

	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel)
	{}

}
