package de.dhpoly.feld.control;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import de.dhpoly.feld.Feld;
import de.dhpoly.karte.model.BezahlKarte;
import de.dhpoly.karte.model.Karte;
import de.dhpoly.kartenstapel.control.KartenstapelImpl;
import de.dhpoly.kartenstapel.model.GeldTransfer;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;

public class FeldEreignisTest
{
	private static boolean ereigniskarteGezeigt = false;

	@Test
	@Ignore
	public void ereigniskarteWirdBeimBetretenGezeigt()
	{
		// TODO fixen
		ereigniskarteGezeigt = false;

		List<Karte> kartenstapel = new ArrayList<>();
		kartenstapel.add(new BezahlKarte("bla", GeldTransfer.ANDERESPIELER_SPIELER,
				new RessourcenDatensatz(Ressource.GELD, 1000)));

		Feld ereignisfeld = new FeldEreignis(new KartenstapelImpl(kartenstapel));
		// ereignisfeld.betreteFeld(spieler, 2, Wetter.BEWOELKT);

		assertTrue(ereigniskarteGezeigt);
	}
}
