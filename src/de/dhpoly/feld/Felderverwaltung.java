package de.dhpoly.feld;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.spieler.Geldhaber;

public class Felderverwaltung
{
	public List<Feld> felder = new ArrayList<>();

	public boolean isNutzerBesitzerAllerStrassen(int strassengruppe, Geldhaber eigentuemer)
	{
		for (Feld feld : felder)
		{
			if (feld.getGruppe() == strassengruppe && !isEigentuemer(feld, eigentuemer))
			{
				return false;
			}
		}

		// keine Stra�e der Gruppe gefunden, deren Eigent�mer ein anderer ist
		return true;
	}

	private boolean isEigentuemer(Feld feld, Geldhaber moeglicherEigentuemer)
	{
		return (feld.getEigentuemer().isPresent() && feld.getEigentuemer().get() == moeglicherEigentuemer);
	}

	public void setFelder(List<Feld> felder)
	{
		this.felder = felder;
	}
}
