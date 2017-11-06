package de.dhpoly.spieler.control;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;

public class SpielerImplTest
{

	@Test
	public void ueberweiseGeld()
	{
		Spieler empfaenger = new SpielerImpl("me", 100, null);
		Spieler sender = new SpielerImpl("ich", 100, null);

		sender.ueberweiseGeld(50, empfaenger);

		assertThat(sender.getBargeld(), Is.is(50));
		assertThat(empfaenger.getBargeld(), Is.is(150));
	}

	public static Spieler getDefaultSpieler()
	{
		return new SpielerImpl("me", 100, null);
	}

	public static Spieler getDefaultSpieler(int geld)
	{
		return new SpielerImpl("me", geld, null);
	}

	public static Spieler getDefaultSpieler(String name, int geld)
	{
		return new SpielerImpl(name, geld, null);
	}

	public static Spieler getDefaultSpieler(int geld, Spiel spiel)
	{
		return new SpielerImpl("me", geld, spiel);
	}
}
