package de.dhpoly.feld;

import java.util.List;
import java.util.Optional;

import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.model.SpielerDaten;

public interface Felderverwaltung
{
	void setFelder(List<Feld> felder);

	boolean isEigentuemer(Feld feld, SpielerDaten eigentuemer);

	boolean isNutzerBesitzerAllerStrassen(int strassengruppe, Optional<SpielerDaten> optional);

	boolean isNutzerBesitzerAllerStrassen(int strassengruppe, SpielerDaten eigentuemer);

	List<Feld> getFelder(Spieler spieler);

}
