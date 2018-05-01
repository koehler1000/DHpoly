package de.dhpoly.feld.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.spieler.Spieler;

public class FelderverwaltungImpl implements Felderverwaltung
{
	private List<Feld> felder = new ArrayList<>();

	@Override
	public boolean isNutzerBesitzerAllerStrassen(int strassengruppe, Spieler eigentuemer)
	{
		for (Feld feld : felder)
		{
			if (feld instanceof FeldStrasse)
			{
				FeldStrasse strasse = (FeldStrasse) feld;
				if (strasse.getGruppe() == strassengruppe && !isEigentuemer(strasse, eigentuemer))
				{
					return false;
				}
			}
		}

		// keine Stra�e der Gruppe gefunden, deren Eigent�mer ein anderer ist
		return true;
	}

	@Override
	public boolean isEigentuemer(Feld feld, Spieler moeglicherEigentuemer)
	{
		return feld.gehoertSpieler(moeglicherEigentuemer.getDaten());
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

	@Override
	public boolean isNutzerBesitzerAllerStrassen(int strassengruppe, Optional<Spieler> spieler)
	{
		if (spieler.isPresent())
		{
			return isNutzerBesitzerAllerStrassen(strassengruppe, spieler.get());
		}
		return false;
	}
}
