package de.dhpoly.spielfeld;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.dhpoly.player.Player;

public class SpielTest {

	private Spiel spiel;
	@Before
	public void vorbereitung(){
		List<Player> spieler = new ArrayList<Player>();
		spieler.add(new Player("Test1", 200));
		spieler.add(new Player("Test2", 300));
		spiel = new Spiel(null, spieler);
	}
	@Test
	public void testaktuellerSpieler() {
		spiel.getAktuellerSpieler();
		assertEquals("Test1", spiel.getAktuellerSpieler().getName());
	}
	
	@Test
	public void testnaechsterSpieler(){
		spiel.naechsterSpieler();
		assertEquals("Test2", spiel.getAktuellerSpieler().getName());
	}

}
