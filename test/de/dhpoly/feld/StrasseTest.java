package de.dhpoly.feld;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.feld.Strasse;
import de.dhpoly.player.Geldhaber;
import de.dhpoly.spieler.model.GeldhaberImpl;

public class StrasseTest
{

	@Test
	public void spielerKauftStrasse()
	{
		Strasse strasse = new Strasse(new Felderverwaltung(), 50, new int[] { 10, 20, 30, 50, 70, 90 }, 1, 3,
				"Badstrasse");
		Geldhaber spieler = new GeldhaberImpl(500);

		assertThat(strasse.isKaufbar(), Is.is(true));

		strasse.kaufe(spieler);

		assertThat(strasse.isKaufbar(), Is.is(false));
		assertThat(strasse.getEigentuemer().get(), Is.is(spieler));
		assertThat(spieler.getBargeld(), Is.is(500 - 50));
	}

	@Test
	public void spielerGeldAendertSichNichtWennErAufDieEigeneStrasseKommt()
	{
		Strasse strasse = new Strasse(new Felderverwaltung(), 50, new int[] { 10, 20, 30, 50, 70, 90 }, 1, 3,
				"Badstrasse");
		Geldhaber spieler = new GeldhaberImpl(500);
		strasse.kaufe(spieler);

		assertThat(spieler.getBargeld(), Is.is(500 - 50));
		strasse.spielerBetrittFeld(spieler); // eigentümer
		assertThat(spieler.getBargeld(), Is.is(450));
	}

}
