package de.dhpoly.feld;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import com.sun.crypto.provider.AESParameters;

import de.dhpoly.player.Player;
import de.dhpoly.spielfeld.Spiel;

public class FelderTest
{

	@Test
	public void spielerStartetAufFeld0()
	{
		List<Feld> felder = new ArrayList<>();
		Felderverwaltung verwaltung = new Felderverwaltung();
		verwaltung.setFelder(felder);
		felder.add(new Strasse(verwaltung, 100, new int[] { 10, 20, 30, 40, 50 }, 1, 1, "test"));
		Player sp1 = new Player("Sp1", 100);

		Assert.assertThat(sp1.getFeldNr(), Is.is(0));
	}

	@Test
	public void spielerKann2FelderLaufen()
	{
		List<Feld> felder = new ArrayList<>();
		Felderverwaltung verwaltung = new Felderverwaltung();
		verwaltung.setFelder(felder);

		felder.add(new Strasse(verwaltung, 100, new int[] { 10, 20, 30, 40, 50 }, 1, 1, "test"));
		felder.add(new Strasse(verwaltung, 100, new int[] { 10, 20, 30, 40, 50 }, 1, 1, "test"));
		felder.add(new Strasse(verwaltung, 100, new int[] { 10, 20, 30, 40, 50 }, 1, 1, "test"));
		felder.add(new Strasse(verwaltung, 100, new int[] { 10, 20, 30, 40, 50 }, 1, 1, "test"));

		Player sp1 = new Player("Sp1", 100);
		List<Player> spieler = new ArrayList<>();
		spieler.add(sp1);

		Spiel spiel = new Spiel(felder, spieler);

		spiel.ruecke(sp1, 2);
		Assert.assertThat(sp1.getFeldNr(), Is.is(2));
	}

	@Test
	public void spielerKannUeberrunden()
	{
		List<Feld> felder = new ArrayList<>();
		Felderverwaltung verwaltung = new Felderverwaltung();
		verwaltung.setFelder(felder);

		felder.add(new Strasse(verwaltung, 100, new int[] { 10, 20, 30, 40, 50 }, 1, 1, "test"));
		felder.add(new Strasse(verwaltung, 100, new int[] { 10, 20, 30, 40, 50 }, 1, 1, "test"));
		felder.add(new Strasse(verwaltung, 100, new int[] { 10, 20, 30, 40, 50 }, 1, 1, "test"));
		felder.add(new Strasse(verwaltung, 100, new int[] { 10, 20, 30, 40, 50 }, 1, 1, "test"));

		Player sp1 = new Player("Sp1", 100);
		List<Player> spieler = new ArrayList<>();
		spieler.add(sp1);

		Spiel spiel = new Spiel(felder, spieler);
		spiel.ruecke(sp1, 4);
		Assert.assertThat(sp1.getFeldNr(), Is.is(0));
	}

}
