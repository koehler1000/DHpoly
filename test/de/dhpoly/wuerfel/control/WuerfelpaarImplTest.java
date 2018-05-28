package de.dhpoly.wuerfel.control;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.dhpoly.wuerfel.model.Wuerfel;
import de.dhpoly.wuerfel.model.WuerfelDaten;

public class WuerfelpaarImplTest
{
	private Wuerfel w1 = new Wuerfel(1);
	private Wuerfel w2 = new Wuerfel(2);

	@Test
	public void wuerfelnWirftAlleWuerfel() throws Exception
	{
		List<Wuerfel> wuerfel = new ArrayList<>();
		wuerfel.add(w1);
		wuerfel.add(w2);
		wuerfel.add(new Wuerfel(4));

		WuerfelpaarImpl wuerfelpaar = new WuerfelpaarImpl(new WuerfelDaten(wuerfel));
		
		assertTrue(wuerfelpaar.getWuerfel().contains(w1));
		assertTrue(wuerfelpaar.getWuerfel().contains(w1));

		wuerfelpaar.wuerfeln();

		assertFalse(wuerfelpaar.getWuerfel().contains(w1));
		assertFalse(wuerfelpaar.getWuerfel().contains(w1));
	}
}
