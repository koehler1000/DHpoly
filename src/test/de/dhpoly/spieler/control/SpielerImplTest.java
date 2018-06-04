package de.dhpoly.spieler.control;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielImpl;
import de.dhpoly.spiel.control.SpielImplTest;
import de.dhpoly.spieler.model.Spieler;

public class SpielerImplTest
{
	private Spiel spiel = new SpielImpl();

	@Test
	public void spielerKannVorGestartetemSpielHinzugefuegtWerden()
	{
		Spieler spieler = getDefaultSpieler();
		spiel.fuegeSpielerHinzu(spieler);

		assertThat(spiel.getSpieler().size(), Is.is(1));
	}

	@Test
	public void spielerKannNachGestartetemSpielNichtMehrHinzugefuegtWerden()
	{
		Spieler spieler = getDefaultSpieler();
		spiel.fuegeSpielerHinzu(getDefaultSpieler());
		spiel.fuegeSpielerHinzu(getDefaultSpieler());
		spiel.starteSpiel();
		spiel.fuegeSpielerHinzu(spieler);

		assertThat(spiel.getSpieler().size(), Is.is(2));
	}

	@Test
	public void ueberweiseGeld()
	{
		Spieler empfaenger = getDefaultSpieler(100);
		Spieler sender = getDefaultSpieler(100);

		RessourcenDatensatz dat = new RessourcenDatensatz(Ressource.GELD, 50);
		sender.auszahlen(dat);
		empfaenger.einzahlen(dat);

		assertThat(sender.getRessourcenWert(Ressource.GELD), Is.is(50));
		assertThat(empfaenger.getRessourcenWert(Ressource.GELD), Is.is(150));
	}

	@Test
	public void spielerErhaeltGeldBeiSpielstart()
	{
		Einstellungen einstellungen = new Einstellungen();
		int guthabenNachStart = einstellungen.getStartguthaben();
		int guthabenVorStart = 0;

		Spiel spiel = SpielImplTest.getDefaultSpiel();
		spiel.setEinstellungen(einstellungen);

		Spieler spieler = getDefaultSpieler(guthabenVorStart);
		spiel.fuegeSpielerHinzu(spieler);

		assertThat(spieler.getRessourcenWert(Ressource.GELD), Is.is(guthabenVorStart));

		spiel.starteSpiel();

		assertThat(spieler.getRessourcenWert(Ressource.GELD), Is.is(guthabenNachStart));
	}

	public static Spieler getDefaultSpieler()
	{
		return getDefaultSpieler(0);
	}

	public static Spieler getDefaultSpieler(int geld)
	{
		return getDefaultSpieler("me", geld);
	}

	public static Spieler getDefaultSpieler(String name, int geld)
	{
		Spieler spieler = new Spieler(name);
		spieler.einzahlen(new RessourcenDatensatz(Ressource.GELD, geld));
		return spieler;
	}
}
