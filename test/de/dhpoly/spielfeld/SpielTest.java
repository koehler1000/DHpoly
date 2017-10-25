package de.dhpoly.spielfeld;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImpl;

public class SpielTest
{

	private Spiel spiel;

	@Before
	public void vorbereitung()
	{
		List<Spieler> spieler = new ArrayList<Spieler>();
		spieler.add(new SpielerImpl("Test1", 200));
		spieler.add(new SpielerImpl("Test2", 300));
		spiel = new Spiel(null, spieler);
	}

	@Test
	public void testaktuellerSpieler()
	{
		spiel.getAktuellerSpieler();
		assertEquals("Test1", spiel.getAktuellerSpieler().getName());
	}

	@Test
	public void testnaechsterSpieler()
	{
		spiel.naechsterSpieler();
		assertEquals("Test2", spiel.getAktuellerSpieler().getName());
	}

}
