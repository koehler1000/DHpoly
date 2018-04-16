package de.dhpoly.spieler.control;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.spiel.Spiel;

public class SpielerComputer extends SpielerImpl
{
	public SpielerComputer(String name, Einstellungen einstellungen, Spiel spiel)
	{
		super(name, einstellungen, spiel);
	}

	@Override
	public void zeigeTransaktionsvorschlag(Transaktion transaktion)
	{
		// TODO AI entscheiden lassen
	}

	@Override
	public void zeigeKaufmoeglichkeit(Strasse strasse)
	{
		// TODO AI entscheiden lassen
	}
}
