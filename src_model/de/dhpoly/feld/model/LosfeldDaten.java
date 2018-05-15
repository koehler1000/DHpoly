package de.dhpoly.feld.model;

import de.dhpoly.spieler.model.Spieler;

public class LosfeldDaten extends FeldDaten
{
	private static final long serialVersionUID = 1L;

	public LosfeldDaten()
	{
		super(FeldTyp.LOS);
	}

	@Override
	public boolean gehoertSpieler(Spieler spieler)
	{
		return false;
	}
}
