package de.dhpoly.feld.model;

import de.dhpoly.spieler.model.Spieler;

public class EreignisfeldDaten extends FeldDaten
{
	private static final long serialVersionUID = 1L;

	public EreignisfeldDaten()
	{
		super(FeldTyp.EREIGNISFELD);
	}

	@Override
	public boolean gehoertSpieler(Spieler spieler)
	{
		return false;
	}
}
