package de.dhpoly.kartenstapel.view;

import org.junit.Test;

import de.dhpoly.karte.control.BezahlKarte;
import de.dhpoly.kartenstapel.model.GeldTransfer;

public class KartenOberflaecheVorschau
{

	@Test
	public void vorschau()
	{
		new KartenOberflaeche(new BezahlKarte("Die Steuer wird fällig, zahle 1000€", GeldTransfer.SPIELER_FREIPARKEN, 1000));
	}

}
