package de.dhpoly.spiel.control;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.dhpoly.spiel.model.SpielStatus;
import de.dhpoly.spieler.model.Spieler;

public class SpielerHinzufuegenLogikTest
{
	private SpielerHinzufuegenLogik logik = new SpielerHinzufuegenLogik();
	private boolean spielerHinzugefuegt = false;

	private SpielUnimplemented spiel = new SpielUnimplemented()
	{
		@Override
		public void fuegeSpielerHinzu(Spieler spieler)
		{
			spielerHinzugefuegt = true;
		}

		@Override
		public SpielStatus getStatus()
		{
			return SpielStatus.SPIEL_VORBEREITUNG;
		}

		public List<Spieler> getSpieler()
		{
			return new ArrayList<>();
		}
	};

	@Test
	public void spielWirdGestartet() throws Exception
	{
		Spieler spieler = new Spieler("bla");
		logik.verarbeite(spieler, spiel);

		assertTrue(spielerHinzugefuegt);
	}
}
