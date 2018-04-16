package de.dhpoly.spieler.control;

import java.util.List;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.feld.Feld;
import de.dhpoly.spiel.Spiel;

public class SpielerLokal extends SpielerImpl
{
	public SpielerLokal(String name, Einstellungen einstellungen, Spiel spiel)
	{
		super(name, einstellungen, spiel);
	}

	public SpielerLokal(String name, Einstellungen einstellungen, Spiel spiel, List<Feld> felder)
	{
		super(name, einstellungen, spiel, felder);
	}

	// TODO Alle Methoden, die NUR lokal verfügbar sind hierher umziehen
}
