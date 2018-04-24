package de.dhpoly.feld;

import java.util.List;
import java.util.Optional;

import de.dhpoly.spieler.Spieler;

public interface Felderverwaltung
{
	void setFelder(List<Feld> felder);

	boolean isEigentuemer(Feld feld, Spieler moeglicherEigentuemer);

	boolean isNutzerBesitzerAllerStrassen(int strassengruppe, Optional<Spieler> optional);

	boolean isNutzerBesitzerAllerStrassen(int strassengruppe, Spieler spieler);

	List<Feld> getFelder(Spieler spieler);
}
