package de.dhpoly.spielfeld;

import java.util.List;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.feld.Feld;

public interface Spielfeld
{
	List<Feld> getStandardSpielfeld(Einstellungen einstellungen);
}
