package de.dhpoly.feld.control;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielImpl;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImpl;

public class FelderTest
{

	@Test
	public void spielerStartetAufFeld0()
	{
		List<Feld> felder = new ArrayList<>();
		Felderverwaltung verwaltung = new FelderverwaltungImpl();
		verwaltung.setFelder(felder);
		felder.add(new Strasse(verwaltung, 100, new int[] { 10, 20, 30, 40, 50 }, 1, 1, "test"));
		Spieler sp1 = new SpielerImpl("Sp1", 100);

		Assert.assertThat(sp1.getFeldNr(), Is.is(0));
	}

	@Test
	public void spielerKann2FelderLaufen()
	{
		List<Feld> felder = new ArrayList<>();
		Felderverwaltung verwaltung = new FelderverwaltungImpl();
		verwaltung.setFelder(felder);

		felder.add(new Strasse(verwaltung, 100, new int[] { 10, 20, 30, 40, 50 }, 1, 1, "test"));
		felder.add(new Strasse(verwaltung, 100, new int[] { 10, 20, 30, 40, 50 }, 1, 1, "test"));
		felder.add(new Strasse(verwaltung, 100, new int[] { 10, 20, 30, 40, 50 }, 1, 1, "test"));
		felder.add(new Strasse(verwaltung, 100, new int[] { 10, 20, 30, 40, 50 }, 1, 1, "test"));

		Spieler sp1 = new SpielerImpl("Sp1", 100);
		List<Spieler> spieler = new ArrayList<>();
		spieler.add(sp1);

		Spiel spiel = new SpielImpl(felder, spieler);

		spiel.ruecke(sp1, 2);
		Assert.assertThat(sp1.getFeldNr(), Is.is(2));
	}

	@Test
	public void spielerKannUeberrunden()
	{
		List<Feld> felder = new ArrayList<>();
		Felderverwaltung verwaltung = new FelderverwaltungImpl();
		verwaltung.setFelder(felder);

		felder.add(new Strasse(verwaltung, 100, new int[] { 10, 20, 30, 40, 50 }, 1, 1, "test"));
		felder.add(new Strasse(verwaltung, 100, new int[] { 10, 20, 30, 40, 50 }, 1, 1, "test"));
		felder.add(new Strasse(verwaltung, 100, new int[] { 10, 20, 30, 40, 50 }, 1, 1, "test"));
		felder.add(new Strasse(verwaltung, 100, new int[] { 10, 20, 30, 40, 50 }, 1, 1, "test"));

		Spieler sp1 = new SpielerImpl("Sp1", 100);
		List<Spieler> spieler = new ArrayList<>();
		spieler.add(sp1);

		Spiel spiel = new SpielImpl(felder, spieler);
		spiel.ruecke(sp1, 4);
		Assert.assertThat(sp1.getFeldNr(), Is.is(0));
	}

}
