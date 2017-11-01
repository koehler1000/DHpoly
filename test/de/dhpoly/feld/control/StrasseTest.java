package de.dhpoly.feld.control;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImpl;

public class StrasseTest
{

	@Test
	public void spielerKauftStrasse()
	{
		Strasse strasse = new Strasse(new FelderverwaltungImpl(), 50, new int[] { 10, 20, 30, 50, 70, 90 }, 1, 3,
				"Badstrasse");
		Spieler spieler = new SpielerImpl("bar", 500);

		assertThat(strasse.isKaufbar(), Is.is(true));

		strasse.kaufe(spieler);

		assertThat(strasse.isKaufbar(), Is.is(false));
		assertThat(strasse.getEigentuemer().get(), Is.is(spieler));
		assertThat(spieler.getBargeld(), Is.is(500 - 50));
	}

	@Test
	public void spielerGeldAendertSichNichtWennErAufDieEigeneStrasseKommt()
	{
		Strasse strasse = new Strasse(new FelderverwaltungImpl(), 50, new int[] { 10, 20, 30, 50, 70, 90 }, 1, 3,
				"Badstrasse");
		Spieler spieler = new SpielerImpl("foo", 500);
		strasse.kaufe(spieler);

		assertThat(spieler.getBargeld(), Is.is(500 - 50));
		strasse.spielerBetrittFeld(spieler); // eigent�mer
		assertThat(spieler.getBargeld(), Is.is(450));
	}

}
