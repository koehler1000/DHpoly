package de.dhpoly.spiel.control;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.dhpoly.spiel.model.SpielStart;
import de.dhpoly.spiel.model.SpielStatus;
import de.dhpoly.spieler.model.Spieler;

public class SpielStartLogikTest
{
	private SpielStartLogik logik = new SpielStartLogik();
	private boolean spielStartAufgerufen = false;

	@Test
	public void spielWirdGestartet() throws Exception
	{
		SpielStart start = new SpielStart(new Spieler(""));
		logik.verarbeite(start, spiel);

		assertTrue(spielStartAufgerufen);
	}

	private SpielUnimplemented spiel = new SpielUnimplemented()
	{
		@Override
		public void starteSpiel()
		{
			spielStartAufgerufen = true;
		}

		@Override
		public SpielStatus getStatus()
		{
			return SpielStatus.SPIEL_VORBEREITUNG;
		}

		public List<Spieler> getSpieler()
		{
			List<Spieler> spieler = new ArrayList<>();
			spieler.add(new Spieler("bla"));
			return spieler;
		}
	};
}
