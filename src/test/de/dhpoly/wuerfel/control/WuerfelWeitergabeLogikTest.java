package de.dhpoly.wuerfel.control;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielUnimplemented;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.wuerfel.model.WuerfelWeitergabe;

public class WuerfelWeitergabeLogikTest {
	public boolean wuerfelWeitergegeben = false;
	private Spiel spiel = new SpielUnimplemented() {
		@Override
		public void wuerfelWeitergeben(Spieler spieler) {
			wuerfelWeitergegeben = true;
		}
	};

	@Test
	public void naechsterSpielerWirdAufgerufenBeiWuerfelAufruf() {
		Spieler spieler = new Spieler("Testa");

		WuerfelWeitergabeLogik logik = new WuerfelWeitergabeLogik();
		WuerfelWeitergabe aufruf = new WuerfelWeitergabe(spieler);

		logik.verarbeite(aufruf, spiel);

		assertTrue(wuerfelWeitergegeben);
	}
}
