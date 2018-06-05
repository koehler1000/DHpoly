package de.dhpoly.wuerfel.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class WuerfelDatenTest
{
	@Test
	public void isPasch() throws Exception
	{
		List<Wuerfel> wuerfel = new ArrayList<>();
		wuerfel.add(new Wuerfel(1));
		wuerfel.add(new Wuerfel(1));

		WuerfelDaten daten = new WuerfelDaten(wuerfel);

		assertTrue(daten.isPasch());
	}

	@Test
	public void isPaschNegative() throws Exception
	{
		List<Wuerfel> wuerfel = new ArrayList<>();
		wuerfel.add(new Wuerfel(1));
		wuerfel.add(new Wuerfel(2));

		WuerfelDaten daten = new WuerfelDaten(wuerfel);

		assertFalse(daten.isPasch());
	}
}
