package de.dhpoly.feld.control;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImplTest;

public class StrasseTest
{

	@Test
	public void spielerKauftStrasse()
	{
		final int startbetrag = 500;
		final int kosten = 50;

		Strasse strasse = new Strasse(new FelderverwaltungImpl(), kosten, new int[] { 10, 20, 30, 50, 70, 90 }, 1, 3,
				"Badstrasse");
		Spieler spieler = SpielerImplTest.getDefaultSpieler(startbetrag);

		assertThat(strasse.isKaufbar(), Is.is(true));

		strasse.kaufe(spieler);

		assertThat(strasse.isKaufbar(), Is.is(false));
		assertThat(strasse.getEigentuemer().get(), Is.is(spieler));
		assertThat(spieler.getBargeld(), Is.is(startbetrag - kosten));
	}

	@Test
	public void spielerGeldAendertSichNichtWennErAufDieEigeneStrasseKommt()
	{
		final int startbetrag = 500;
		final int kosten = 50;

		Strasse strasse = new Strasse(new FelderverwaltungImpl(), kosten, new int[] { 10, 20, 30, 50, 70, 90 }, 1, 3,
				"Badstrasse");
		Spieler spieler = SpielerImplTest.getDefaultSpieler(startbetrag);
		strasse.kaufe(spieler);

		assertThat(spieler.getBargeld(), Is.is(startbetrag - kosten));
		strasse.spielerBetrittFeld(spieler); // eigentümer
		assertThat(spieler.getBargeld(), Is.is(startbetrag - kosten));
	}

	public static Strasse getDefaultStrasse()
	{
		return getDefaultStrasse("test");
	}

	public static Strasse getDefaultStrasse(String name)
	{
		return getDefaultStrasse(name, 0);
	}

	public static Strasse getDefaultStrasse(String name, int preis)
	{
		return new Strasse(null, preis, new int[1], 1, 1, name);
	}
}
