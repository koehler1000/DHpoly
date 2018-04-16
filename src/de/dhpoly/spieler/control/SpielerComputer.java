package de.dhpoly.spieler.control;

import java.util.List;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.spiel.Spiel;

public class SpielerComputer extends SpielerImpl
{
	public SpielerComputer(String name, Einstellungen einstellungen, Spiel spiel, List<Feld> felder)
	{
		super(name, einstellungen, spiel, felder);
	}

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
