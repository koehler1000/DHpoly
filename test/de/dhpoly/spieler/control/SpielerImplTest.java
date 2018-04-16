package de.dhpoly.spieler.control;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.einstellungen.model.EinstellungenImpl;
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
		return new SpielerLokal("me", new EinstellungenImpl(), null);
	}

	public static Spieler getDefaultSpieler(int geld)
	{
		return getDefaultSpieler("me", geld);
	}

	public static Spieler getDefaultSpieler(String name, int geld)
	{
		return getDefaultSpieler(name, geld, null);
	}

	public static Spieler getDefaultSpieler(int geld, Spiel spiel)
	{
		return getDefaultSpieler("me", geld, spiel);
	}

	public static Spieler getDefaultSpieler(String name, Spiel spiel)
	{
		return getDefaultSpieler(name, 1000, spiel);
	}

	public static Spieler getDefaultSpieler(String name, int geld, Spiel spiel)
	{
		Einstellungen einstellungen = new EinstellungenImpl();
		einstellungen.setStartguthaben(geld);
		return new SpielerLokal(name, einstellungen, spiel);
	}
}
