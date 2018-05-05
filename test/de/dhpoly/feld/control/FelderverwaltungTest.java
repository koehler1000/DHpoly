package de.dhpoly.feld.control;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.dhpoly.feld.Feld;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielImpl;
import de.dhpoly.spieler.control.SpielerImplTest;
import de.dhpoly.spieler.model.Spieler;

public class FelderverwaltungTest
{
	@Test
	public void nichtEigentuemerAllerStrassenWennStrassenNochNichtVerkauft()
	{
		List<Feld> felder = new ArrayList<>();
		felder.add(FelderTest.getDefaultFeld(1));
		felder.add(FelderTest.getDefaultFeld(1));

		Spieler spieler = SpielerImplTest.getDefaultSpieler();

		FeldStrasse strasse = (FeldStrasse) felder.get(0);
		strasse.kaufe(spieler);

		Spiel spiel = new SpielImpl();
		spiel.setFelder(felder);

		assertFalse(spieler.hatAlleStrassenDerGruppe(1));
	}

	@Test
	public void spielerBesitztAlleStrassenWennErAlleGekauftHat()
	{
		List<Feld> felder = new ArrayList<>();

		FeldStrasse s1 = FelderTest.getDefaultFeld(1);
		FeldStrasse s2 = FelderTest.getDefaultFeld(1);
		FeldStrasse s3 = FelderTest.getDefaultFeld(2);

		felder.add(s1);
		felder.add(s2);
		felder.add(s3);

		Spieler spieler = SpielerImplTest.getDefaultSpieler();

		Spiel spiel = new SpielImpl();
		spiel.setFelder(felder);

		s1.kaufe(spieler);
		s2.kaufe(spieler);

		assertTrue(spieler.hatAlleStrassenDerGruppe(1));
	}

	@Test
	public void spielerBesitztNichtAlleStrassen()
	{
		List<Feld> felder = new ArrayList<>();
		felder.add(FelderTest.getDefaultFeld(1));
		felder.add(FelderTest.getDefaultFeld(1));

		Spieler spieler = SpielerImplTest.getDefaultSpieler();
		FeldStrasse strasse = (FeldStrasse) felder.get(0);
		strasse.kaufe(spieler);

		Spiel spiel = new SpielImpl();
		spiel.setFelder(felder);

		assertFalse(spieler.hatAlleStrassenDerGruppe(1));
	}
}
