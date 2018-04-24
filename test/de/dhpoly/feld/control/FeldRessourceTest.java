package de.dhpoly.feld.control;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.einstellungen.model.EinstellungenImpl;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielImplTest;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImplTest;

public class FeldRessourceTest
{
	@Test
	public void spielerErhaeltRessourceBeimBetreten()
	{
		Einstellungen einstellungen = new EinstellungenImpl();
		einstellungen.setRessourcenErtrag(10);
		einstellungen.setStartguthaben(0);

		FeldRessource feld = new FeldRessource(Ressource.HOLZ, einstellungen);
		Spiel spiel = SpielImplTest.getDefaultSpiel(einstellungen);

		Spieler spieler = SpielerImplTest.getDefaultSpieler(spiel);

		spiel.fuegeSpielerHinzu(spieler);

		feld.betreteFeld(spieler, 2, Wetter.BEWOELKT);

		assertThat(spieler.getRessourcenWerte(Ressource.GELD), Is.is(0));
		assertThat(spieler.getRessourcenWerte(Ressource.HOLZ), Is.is(10));
		assertThat(spieler.getRessourcenWerte(Ressource.STEIN), Is.is(0));
	}
}
