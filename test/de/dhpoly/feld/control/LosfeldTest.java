package de.dhpoly.feld.control;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.einstellungen.control.EinstellungenImpl;
import de.dhpoly.feld.Feld;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImplTest;

public class LosfeldTest
{

	@Test
	public void test()
	{
		Spieler s1 = SpielerImplTest.getDefaultSpieler(100);
		Losfeld los = new Losfeld(new EinstellungenImpl());
		los.betreteFeld(s1, 2, Wetter.BEWOELKT);

		assertThat(s1.getBargeld(), Is.is(2100));
	}

	public static Feld getDefaultFeld()
	{
		return new Losfeld(new EinstellungenImpl());
	}
}
