package de.dhpoly.wuerfel.control;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielUnimplemented;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.wuerfel.model.WuerfelAufruf;

public class WuerfelAufrufLogikTest
{
	public boolean gewuerfelt = false;
	private Spiel spiel = new SpielUnimplemented()
	{
		@Override
		public void wuerfeln(Spieler spieler)
		{
			gewuerfelt = true;
		}
	};

	@Test
	public void naechsterSpielerWirdAufgerufenBeiWuerfelAufruf()
	{
		Spieler spieler = new Spieler("Testa");

		WuerfelAufrufLogik logik = new WuerfelAufrufLogik();
		WuerfelAufruf aufruf = new WuerfelAufruf(spieler);

		logik.verarbeite(aufruf, spiel);

		assertTrue(gewuerfelt);
	}
}
