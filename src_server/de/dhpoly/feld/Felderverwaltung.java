package de.dhpoly.feld;

import java.util.List;
import java.util.Optional;

import de.dhpoly.spieler.model.Spieler;

public interface Felderverwaltung
{
	void setFelder(List<Feld> felder);

	boolean isEigentuemer(Feld feld, Spieler eigentuemer);

	boolean isNutzerBesitzerAllerStrassen(int strassengruppe, Optional<Spieler> optional);

	boolean isNutzerBesitzerAllerStrassen(int strassengruppe, Spieler eigentuemer);

	List<Feld> getFelder(Spieler spieler);

}
