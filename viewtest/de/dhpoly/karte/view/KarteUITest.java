package de.dhpoly.karte.view;

import org.junit.Test;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.karte.control.BezahlKarte;
import de.dhpoly.karte.view.KarteUI;
import de.dhpoly.kartenstapel.model.GeldTransfer;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.ressource.control.RessourcenDatensatzImpl;
import de.dhpoly.ressource.model.Ressource;

public class KarteUITest // NOSONAR
{
	@Test
	public void vorschau()
	{
		new Fenster(new Bilderverwalter())
				.zeigeComponente(new KarteUI(new BezahlKarte("Die Steuer wird f�llig, zahle 1000�",
						GeldTransfer.BANK_SPIELER, new RessourcenDatensatzImpl(Ressource.GELD, 1000))));
	}
}
