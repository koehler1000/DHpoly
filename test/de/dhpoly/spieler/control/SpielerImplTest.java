package de.dhpoly.spieler.control;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.einstellungen.control.EinstellungenImpl;
import de.dhpoly.ressource.control.RessourcenDatensatzImpl;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;

public class SpielerImplTest
{
	@Test
	public void ueberweiseGeld()
	{
		Spieler empfaenger = getDefaultSpieler(100);
		Spieler sender = getDefaultSpieler(100);

		sender.ueberweise(new RessourcenDatensatzImpl(Ressource.GELD, 50), empfaenger);

		assertThat(sender.getRessourcenWerte(Ressource.GELD), Is.is(50));
		assertThat(empfaenger.getRessourcenWerte(Ressource.GELD), Is.is(150));
	}

	public static Spieler getDefaultSpieler()
	{
		return new SpielerImpl("me", new EinstellungenImpl(), null);
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

	public static Spieler getDefaultSpieler(String name, Spiel spiel)
	{
		return new SpielerImpl(name, 1000, spiel);
	}
}
