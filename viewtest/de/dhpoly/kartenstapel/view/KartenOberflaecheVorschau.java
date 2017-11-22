package de.dhpoly.kartenstapel.view;

import org.junit.Test;

import de.dhpoly.karte.control.BezahlKarte;
import de.dhpoly.kartenstapel.model.GeldTransfer;
import de.dhpoly.ressource.control.RessourcenDatensatzImpl;
import de.dhpoly.ressource.model.Ressource;

public class KartenOberflaecheVorschau
{
	@Test
	public void vorschau()
	{
		new KartenOberflaeche(new BezahlKarte("Die Steuer wird fällig, zahle 1000€", GeldTransfer.BANK_SPIELER,
				new RessourcenDatensatzImpl(Ressource.GELD, 1000)));
	}
}
