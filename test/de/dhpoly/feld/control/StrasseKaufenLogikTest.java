package de.dhpoly.feld.control;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.feld.model.StrasseKaufen;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielUnimplemented;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.model.SpielerTyp;

public class StrasseKaufenLogikTest
{
	private StrasseKaufenLogik logik = new StrasseKaufenLogik();
	private Spieler spieler = new Spieler(SpielerTyp.COMPUTER, "Peter");
	private Spieler aktuellerSpieler = new Spieler(SpielerTyp.COMPUTER, "ba");

	@Test
	public void testPositiv()
	{
		StrasseDaten strasse = new StrasseDaten();
		StrasseKaufen kauf = new StrasseKaufen(strasse, spieler);
		aktuellerSpieler = spieler;
		logik.verarbeite(kauf, spiel);

		assertTrue(strasse.gehoertSpieler(spieler));
	}

	@Test
	public void testNegativ()
	{
		StrasseDaten strasse = new StrasseDaten();
		StrasseKaufen kauf = new StrasseKaufen(strasse, spieler);
		logik.verarbeite(kauf, spiel);

		assertFalse(strasse.gehoertSpieler(spieler));
	}

	private Spiel spiel = new SpielUnimplemented()
	{
		@Override
		public Spieler getAktuellerSpieler()
		{
			return aktuellerSpieler;
		}
	};
}
