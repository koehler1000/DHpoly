package de.dhpoly.spieler.control;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.model.SpielerDaten;
import de.dhpoly.spieler.model.SpielerTyp;

public class SpielerImpl implements Spieler
{
	SpielerDaten daten;

	public SpielerImpl(String name, Spiel spiel)
	{
		this.daten = new SpielerDaten(SpielerTyp.COMPUTER, name);
	}

	@Override
	public SpielerDaten getDaten()
	{
		return daten;
	}

	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel)
	{
		// TODO Auto-generated method stub

	}
}
