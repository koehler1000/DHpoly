package de.dhpoly.kartenstapel.view;

import org.junit.Test;

import de.dhpoly.kartenstapel.model.GeldTransfer;
import de.dhpoly.kartenstapel.model.Karte;

public class KartenOberflaecheVorschau
{

	@Test
	public void vorschau()
	{
		new KartenOberflaeche(new Karte("Die Steuer wird fällig, zahle 1000€", GeldTransfer.SPIELER_FREIPARKEN, 1000));
	}

}
