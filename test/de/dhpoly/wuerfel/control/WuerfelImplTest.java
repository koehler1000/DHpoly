package de.dhpoly.wuerfel.control;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WuerfelImplTest
{

	@Test
	public void wuerfelnLiefertZahlZwischenEinsUndSechs()
	{
		int wuerfelErgebnis = new WuerfelImpl().wuerfeln();
		assertTrue(wuerfelErgebnis >= 1);
		assertTrue(wuerfelErgebnis <= 6);
	}

	@Test
	public void paschWirdErkannt()
	{
		assertTrue(new WuerfelImpl().isPasch(1, 1));
	}

	@Test
	public void keinPaschWirdErkannt()
	{
		assertFalse(new WuerfelImpl().isPasch(1, 2));
	}

}
