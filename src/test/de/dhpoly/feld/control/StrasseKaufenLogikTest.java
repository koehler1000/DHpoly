package de.dhpoly.feld.control;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.feld.model.StrasseKaufen;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielUnimplemented;
import de.dhpoly.spiel.model.SpielfeldDaten;
import de.dhpoly.spieler.model.Spieler;

public class StrasseKaufenLogikTest
{
	private Datenobjekt gesendetesObjekt;
	private StrasseKaufenLogik logik = new StrasseKaufenLogik();
	private Spieler spieler = new Spieler("Peter");
	private Spieler aktuellerSpieler = new Spieler("ba");

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

	@Test
	public void strassenKaufSendetStrasseAnClient()
	{
		StrasseDaten strasse = new StrasseDaten();
		StrasseKaufen kauf = new StrasseKaufen(strasse, spieler);
		aktuellerSpieler = spieler;

		logik.verarbeite(kauf, spiel);

		assertTrue(gesendetesObjekt instanceof SpielfeldDaten);
	}

	private Spiel spiel = new SpielUnimplemented()
	{
		@Override
		public Spieler getAktuellerSpieler()
		{
			return aktuellerSpieler;
		}

		@Override
		public void zeigeAllenSpielern(Datenobjekt datenobjekt)
		{
			gesendetesObjekt = datenobjekt;
		}

		@Override
		public SpielfeldDaten getSpielfeld()
		{
			return new SpielfeldDaten(null);
		}

		@Override
		public boolean kannSpielerStrasseKaufen(Spieler spieler, StrasseDaten strasse)
		{
			return aktuellerSpieler.equals(spieler);
		}
	};
}
