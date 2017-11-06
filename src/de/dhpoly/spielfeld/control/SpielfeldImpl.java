package de.dhpoly.spielfeld.control;

import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.spielfeld.Spielfeld;
import de.dhpoly.spielfeld.model.Standardspielfeld;

public class SpielfeldImpl implements Spielfeld
{

	@Override
	public List<Feld> getStandardSpielfeld()
	{
		return new Standardspielfeld().getStandardSpielfeld();
	}
}
