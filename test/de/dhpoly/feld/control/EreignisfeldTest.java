package de.dhpoly.feld.control;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.dhpoly.feld.Feld;
import de.dhpoly.karte.Karte;
import de.dhpoly.karte.control.BezahlKarte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.kartenstapel.control.KartenstapelImpl;
import de.dhpoly.kartenstapel.model.GeldTransfer;
import de.dhpoly.ressource.control.RessourcenDatensatzImpl;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerUnimplemented;

public class EreignisfeldTest
{
	private static boolean ereigniskarteGezeigt = false;

	@Test
	public void ereigniskarteWirdBeimBetretenGezeigt()
	{
		ereigniskarteGezeigt = false;

		Spieler spieler = new SpielerUnimplemented()
		{
			@Override
			public void zeigeKarte(Karte karte)
			{
				ereigniskarteGezeigt = true;
			}
		};

		List<Karte> kartenstapel = new ArrayList<>();
		kartenstapel.add(new BezahlKarte("bla", GeldTransfer.ANDERESPIELER_SPIELER,
				new RessourcenDatensatzImpl(Ressource.GELD, 1000)));

		Feld ereignisfeld = new Ereignisfeld(new KartenstapelImpl(kartenstapel));
		ereignisfeld.betreteFeld(spieler, 2, Wetter.BEWOELKT);

		assertTrue(ereigniskarteGezeigt);
	}
}
