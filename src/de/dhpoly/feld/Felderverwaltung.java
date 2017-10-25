package de.dhpoly.feld;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.spieler.Spieler;

public class Felderverwaltung
{
	public List<Feld> felder = new ArrayList<>();

	public boolean isNutzerBesitzerAllerStrassen(int strassengruppe, Spieler eigentuemer)
	{
		for (Feld feld : felder)
		{
			if (feld.getGruppe() == strassengruppe && !isEigentuemer(feld, eigentuemer))
			{
				return false;
			}
		}

		// keine Straße der Gruppe gefunden, deren Eigentümer ein anderer ist
		return true;
	}

	private boolean isEigentuemer(Feld feld, Spieler moeglicherEigentuemer)
	{
		return (feld.getEigentuemer().isPresent() && feld.getEigentuemer().get() == moeglicherEigentuemer);
	}

	public void setFelder(List<Feld> felder)
	{
		this.felder = felder;
	}
}
