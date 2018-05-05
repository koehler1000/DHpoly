package de.dhpoly.spieler.control;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.model.SpielerDaten;
import de.dhpoly.spieler.model.SpielerTyp;

public class SpielerComputer extends SpielerImpl
{
	public SpielerComputer(String name, Spiel spiel)
	{
		super(new SpielerDaten(SpielerTyp.COMPUTER, name), spiel);
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
