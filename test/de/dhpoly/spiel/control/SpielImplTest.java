package de.dhpoly.spiel.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.StrasseTest;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.model.Balancing;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImpl;

public class SpielImplTest
{

	private Spiel spiel;

	@Before
	public void vorbereitung()
	{
		List<Spieler> spieler = new ArrayList<Spieler>();
		spieler.add(new SpielerImpl("Test1", 200));
		spieler.add(new SpielerImpl("Test2", 300));

		List<Feld> felder = new ArrayList<>();
		felder.add(StrasseTest.getDefaultStrasse());
		felder.add(StrasseTest.getDefaultStrasse());

		spiel = new SpielImpl(felder, spieler);
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

	@Test
	public void geldBeiUeberLos()
	{
		int geldVorDemLaufen = spiel.getAktuellerSpieler().getBargeld();

		spiel.ruecke(spiel.getAktuellerSpieler(), 2);

		assertThat(spiel.getAktuellerSpieler().getBargeld(), Is.is(geldVorDemLaufen + Balancing.UEBER_LOS));
	}

}
