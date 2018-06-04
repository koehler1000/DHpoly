package de.dhpoly.feld.control;

import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielImplTest;
import de.dhpoly.spieler.control.SpielerImplTest;
import de.dhpoly.spieler.model.Spieler;

public class FeldStrasseTest
{
	@Test
	public void spielerKauftStrasse()
	{
		final int startbetrag = 500;
		final int kosten = 50;

		FeldStrasse strasse = new FeldStrasse(kosten, new int[] { 10, 20, 30, 50, 70, 90 },
				new Einstellungen().getHauskosten(1), 3, "Badstrasse");
		Spieler spieler = SpielerImplTest.getDefaultSpieler(startbetrag);

		assertThat(strasse.isKaufbar(), Is.is(true));

		strasse.kaufe(spieler);

		assertThat(strasse.isKaufbar(), Is.is(false));
		assertThat(strasse.getEigentuemer().get(), Is.is(spieler));
		assertThat(spieler.getRessourcenWert(Ressource.GELD), Is.is(startbetrag - kosten));
	}

	@Test
	public void spielerGeldAendertSichNichtWennErAufDieEigeneStrasseKommt()
	{
		final int startbetrag = 500;
		final int kosten = 50;

		FeldStrasse strasse = new FeldStrasse(kosten, new int[] { 10, 20, 30, 50, 70, 90 },
				new Einstellungen().getHauskosten(1), 3, "Badstrasse");

		Spiel spiel = SpielImplTest.getDefaultSpiel();
		Spieler spieler = new Spieler("Peter");
		spieler.einzahlen(new RessourcenDatensatz(Ressource.GELD, startbetrag));

		strasse.kaufe(spieler);

		spiel.fuegeSpielerHinzu(spieler);

		assertThat(spieler.getRessourcenWert(Ressource.GELD), Is.is(startbetrag - kosten));
		spiel.setWetter(Wetter.BEWOELKT);
		strasse.spielerBetrittFeld(spieler, spiel); // eigentümer
		assertThat(spieler.getRessourcenWert(Ressource.GELD), Is.is(startbetrag - kosten));
	}

	static FeldStrasse getDefaultStrasse(List<RessourcenDatensatz> kostenHaus)
	{
		return new FeldStrasse(0, new int[] { 1, 2, 3 }, kostenHaus, 1, "test");
	}

	public static FeldStrasse getDefaultStrasse()
	{
		return getDefaultStrasse("test");
	}

	public static FeldStrasse getDefaultStrasse(String name)
	{
		return getDefaultStrasse(name, 0);
	}

	public static FeldStrasse getDefaultStrasse(String name, int preis)
	{
		return getDefaultStrasse(name, preis, new int[1]);
	}

	public static FeldStrasse getDefaultStrasse(String name, int preis, int[] mieten)
	{
		return new FeldStrasse(preis, mieten, new Einstellungen().getHauskosten(1), 1, name);
	}

	public static FeldStrasse getDefaultStrasse(Spieler s1)
	{
		FeldStrasse strasse = new FeldStrasse(0, new int[1], new Einstellungen().getHauskosten(1), 1, "Strasse");
		strasse.setEigentuemer(s1);
		return strasse;
	}

	public static FeldStrasse getDefaultStrasse(int[] miete)
	{
		return getDefaultStrasse("Teststrasse", 1000, miete);
	}
}
