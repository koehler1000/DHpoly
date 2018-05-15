package de.dhpoly.spielfeld;

import java.util.List;

import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.feld.model.FeldDaten;

public interface Spielfeld
{
	List<FeldDaten> getStandardSpielfeld(Einstellungen einstellungen);
}
