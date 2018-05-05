package de.dhpoly.feld.control;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import de.dhpoly.feld.Feld;
import de.dhpoly.spiel.control.SpielImpl;
import de.dhpoly.spiel.control.SpielImplTest;
import de.dhpoly.spieler.control.SpielerImplTest;
import de.dhpoly.spieler.model.Spieler;

public class FelderTest
{
	@Test
	public void spielerStartetAufFeld0()
	{
		List<Feld> felder = new ArrayList<>();

		felder.add(getDefaultFeld());

		Spieler sp1 = SpielerImplTest.getDefaultSpieler();

		Assert.assertThat(sp1.getFeldNr(), Is.is(0));
	}

	@Test
	public void spielerKann2FelderLaufen() throws InterruptedException
	{
		List<Feld> felder = new ArrayList<>();

		felder.add(getDefaultFeld());
		felder.add(getDefaultFeld());
		felder.add(getDefaultFeld());
		felder.add(getDefaultFeld());

		SpielImpl spiel = SpielImplTest.getDefaultSpiel();
		spiel.setFelder(felder);

		Spieler sp1 = SpielerImplTest.getDefaultSpieler();
		spiel.fuegeSpielerHinzu(sp1);
		spiel.starteSpiel();

		Thread thread = spiel.rueckeThread(sp1, 2);
		thread.start();
		thread.join();

		Assert.assertThat(sp1.getFeldNr(), Is.is(2));
	}

	@Test
	public void spielerKannUeberrunden()
	{
		List<Feld> felder = new ArrayList<>();

		felder.add(getDefaultFeld());
		felder.add(getDefaultFeld());
		felder.add(getDefaultFeld());
		felder.add(getDefaultFeld());

		SpielImpl spiel = SpielImplTest.getDefaultSpiel();
		spiel.setFelder(felder);

		Spieler sp1 = SpielerImplTest.getDefaultSpieler();
		spiel.fuegeSpielerHinzu(sp1);

		spiel.ruecke(sp1, 4);
		Assert.assertThat(sp1.getFeldNr(), Is.is(0));
	}

	public static Feld getDefaultFeld()
	{
		return getDefaultFeld(0);
	}

	public static FeldStrasse getDefaultFeld(int strassenGruppe)
	{
		return new FeldStrasse(100, new int[] { 10, 20, 30, 40, 50 }, new ArrayList<>(), strassenGruppe, "test");
	}
}
