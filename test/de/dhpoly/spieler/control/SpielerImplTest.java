package de.dhpoly.spieler.control;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielImplTest;
import de.dhpoly.spieler.Spieler;

public class SpielerImplTest
{
	@Test
	public void ueberweiseGeld()
	{
		Spieler empfaenger = getDefaultSpieler(100);
		Spieler sender = getDefaultSpieler(100);

		RessourcenDatensatz dat = new RessourcenDatensatz(Ressource.GELD, 50);
		sender.getDaten().auszahlen(dat);
		empfaenger.getDaten().einzahlen(dat);

		assertThat(sender.getDaten().getRessourcenWert(Ressource.GELD), Is.is(50));
		assertThat(empfaenger.getDaten().getRessourcenWert(Ressource.GELD), Is.is(150));
	}

	@Test
	public void spielerErhaeltGeldBeiSpielstart()
	{
		Einstellungen einstellungen = new Einstellungen();
		int guthabenNachStart = einstellungen.getStartguthaben();
		int guthabenVorStart = 0;

		Spiel spiel = SpielImplTest.getDefaultSpiel();
		spiel.setEinstellungen(einstellungen);

		Spieler spieler = getDefaultSpieler(guthabenVorStart, spiel);
		spiel.fuegeSpielerHinzu(spieler);

		assertThat(spieler.getDaten().getRessourcenWert(Ressource.GELD), Is.is(guthabenVorStart));

		spiel.starteSpiel();

		assertThat(spieler.getDaten().getRessourcenWert(Ressource.GELD), Is.is(guthabenNachStart));
	}

	public static Spieler getDefaultSpieler()
	{
		return new SpielerLokal("me", null);
	}

	public static Spieler getDefaultSpieler(Spiel spiel)
	{
		return new SpielerLokal("me", spiel);
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
		Spieler spieler = new SpielerLokal(name, spiel);
		spieler.getDaten().einzahlen(new RessourcenDatensatz(Ressource.GELD, geld));
		return spieler;
	}
}
