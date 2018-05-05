package de.dhpoly.feld.control;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.dhpoly.feld.Feld;
import de.dhpoly.karte.model.BezahlKarte;
import de.dhpoly.karte.model.Karte;
import de.dhpoly.kartenstapel.control.KartenstapelImpl;
import de.dhpoly.kartenstapel.model.GeldTransfer;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielUnimplemented;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.model.SpielerTyp;

public class FeldEreignisTest
{
	private static boolean ereigniskarteGezeigt = false;

	@Test
	public void ereigniskarteWirdBeimBetretenGezeigt()
	{
		// TODO fixen
		ereigniskarteGezeigt = false;
		Spieler spieler = new Spieler(SpielerTyp.LOKAL, "Armes Würstchen");

		Spiel spiel = new SpielUnimplemented()
		{
			@Override
			public void verarbeiteKarte(Karte karte)
			{
				ereigniskarteGezeigt = true;
			}
		};

		List<Karte> kartenstapel = new ArrayList<>();
		kartenstapel.add(new BezahlKarte("bla", GeldTransfer.ANDERESPIELER_SPIELER,
				new RessourcenDatensatz(Ressource.GELD, 1000)));

		Feld ereignisfeld = new FeldEreignis(new KartenstapelImpl(kartenstapel));
		ereignisfeld.betreteFeld(spieler, 2, spiel);

		assertTrue(ereigniskarteGezeigt);
	}
}
