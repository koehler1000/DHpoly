package de.dhpoly.feld.control;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.dhpoly.feld.model.FeldDaten;
import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.feld.model.StrasseKaufen;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielImpl;
import de.dhpoly.spieler.control.SpielerImplTest;
import de.dhpoly.spieler.model.Spieler;

public class FelderverwaltungTest
{
	@Test
	public void nichtEigentuemerAllerStrassenWennStrassenNochNichtVerkauft()
	{
		List<FeldDaten> felder = new ArrayList<>();

		StrasseDaten s1 = new StrasseDaten();
		s1.setGruppe(1);

		StrasseDaten s2 = new StrasseDaten();
		s2.setGruppe(1);

		felder.add(s1);
		felder.add(s2);

		Spieler spieler = SpielerImplTest.getDefaultSpieler();

		Spiel spiel = new SpielImpl();
		spiel.fuegeSpielerHinzu(spieler);
		spiel.setFelder(felder);

		spiel.kaufe(new StrasseKaufen(s1, spieler), spieler);

		assertFalse(spieler.hatAlleStrassenDerGruppe(1));
	}

	@Test
	public void spielerBesitztAlleStrassenWennErAlleGekauftHat()
	{
		List<FeldDaten> felder = new ArrayList<>();

		StrasseDaten s1 = new StrasseDaten();
		s1.setGruppe(1);
		StrasseDaten s2 = new StrasseDaten();
		s2.setGruppe(1);
		StrasseDaten s3 = new StrasseDaten();
		s3.setGruppe(2);

		felder.add(s1);
		felder.add(s2);
		felder.add(s3);

		Spieler spieler = SpielerImplTest.getDefaultSpieler(99999);

		Spiel spiel = new SpielImpl();
		spiel.fuegeSpielerHinzu(spieler);
		spiel.setFelder(felder);

		spiel.kaufe(new StrasseKaufen(s1, spieler), spieler);
		spiel.kaufe(new StrasseKaufen(s2, spieler), spieler);

		assertTrue(spieler.hatAlleStrassenDerGruppe(1));
	}

	@Test
	public void spielerBesitztNichtAlleStrassen()
	{
		StrasseDaten s1 = new StrasseDaten();
		s1.setGruppe(1);
		StrasseDaten s2 = new StrasseDaten();
		s2.setGruppe(1);

		List<FeldDaten> felder = new ArrayList<>();
		felder.add(s1);
		felder.add(s2);

		Spieler spieler = SpielerImplTest.getDefaultSpieler();

		Spiel spiel = new SpielImpl();
		spiel.fuegeSpielerHinzu(spieler);
		spiel.setFelder(felder);
		spiel.kaufe(new StrasseKaufen(s1, spieler), spieler);

		assertFalse(spieler.hatAlleStrassenDerGruppe(1));
	}
}
