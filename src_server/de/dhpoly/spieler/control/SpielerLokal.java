package de.dhpoly.spieler.control;

import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.model.SpielerDaten;
import de.dhpoly.spieler.model.SpielerTyp;

public class SpielerLokal extends SpielerImpl
{
	public SpielerLokal(String name, Spiel spiel)
	{
		super(new SpielerDaten(SpielerTyp.LOKAL, name), spiel);
	}

	@Override
	public void setWuerfelnMoeglich(boolean value)
	{}

	@Override
	public void setWuerfelWeitergabeMoeglich(boolean value)
	{}
}
