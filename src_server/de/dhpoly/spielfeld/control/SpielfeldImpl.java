package de.dhpoly.spielfeld.control;

import java.util.List;

import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.feld.model.FeldDaten;
import de.dhpoly.spielfeld.Spielfeld;
import de.dhpoly.spielfeld.model.Standardspielfeld;

public class SpielfeldImpl implements Spielfeld
{
	// TODO diese Klasse ausbauen

	@Override
	public List<FeldDaten> getStandardSpielfeld(Einstellungen einstellungen)
	{
		return new Standardspielfeld().getStandardSpielfeld(einstellungen);
	}
}
