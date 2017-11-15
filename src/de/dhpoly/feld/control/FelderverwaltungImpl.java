package de.dhpoly.feld.control;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.spieler.Spieler;

public class FelderverwaltungImpl implements Felderverwaltung
{
	public List<Feld> felder = new ArrayList<>();

	@Override
	public boolean isNutzerBesitzerAllerStrassen(int strassengruppe, Spieler eigentuemer)
	{
		for (Feld feld : felder)
		{
			if (feld instanceof Strasse)
			{
				Strasse strasse = (Strasse) feld;
				if (strasse.getGruppe() == strassengruppe && !isEigentuemer(strasse, eigentuemer))
				{
					return false;
				}
			}
		}

		// keine Straße der Gruppe gefunden, deren Eigentümer ein anderer ist
		return true;
	}

	@Override
	public boolean isEigentuemer(Feld feld, Spieler moeglicherEigentuemer)
	{
		return feld.gehoertSpieler(moeglicherEigentuemer);
	}

	@Override
	public void setFelder(List<Feld> felder)
	{
		this.felder = felder;
	}

	@Override
	public List<Feld> getFelder(Spieler spieler)
	{
		List<Feld> felderVonSpieler = new ArrayList<>();

		for (Feld feld : felder)
		{
			if (isEigentuemer(feld, spieler))
			{
				felderVonSpieler.add(feld);
			}
		}

		return felderVonSpieler;
	}
}
