package de.dhpoly.feld;

import java.util.List;

import de.dhpoly.spieler.Spieler;

public interface Felderverwaltung
{
	void setFelder(List<Feld> felder);

	boolean isEigentuemer(Feld feld, Spieler moeglicherEigentuemer);

	boolean isNutzerBesitzerAllerStrassen(int strassengruppe, Spieler eigentuemer);

	List<Feld> getFelder(Spieler spieler);
}
