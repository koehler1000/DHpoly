package de.dhpoly.strasse;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.spieler.Geldhaber;

public class Strassenverwaltung
{
	public List<Strasse> strassen = new ArrayList<>();

	public boolean isNutzerBesitzerAllerStrassen(int strassengruppe, Geldhaber eigentuemer)
	{
		for (Strasse strasse : strassen)
		{
			if (strasse.getGruppe() == strassengruppe && !isEigentuemer(strasse, eigentuemer))
			{
				return false;
			}
		}

		// keine Straße der Gruppe gefunden, deren Eigentümer ein anderer ist
		return true;
	}

	private boolean isEigentuemer(Strasse strasse, Geldhaber moeglicherEigentuemer)
	{
		return (strasse.getEigentuemer().isPresent() && strasse.getEigentuemer().get() == moeglicherEigentuemer);
	}

	public void setStrassen(List<Strasse> strassen)
	{
		this.strassen = strassen;
	}
}
