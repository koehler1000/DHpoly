package de.dhpoly.feld.control;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.feld.Feld;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielImplTest;
import de.dhpoly.spieler.control.SpielerImplTest;
import de.dhpoly.spieler.model.Spieler;

public class FeldLosTest
{
	@Test
	public void spielerBetrittLosUndErhaeltEingestelltesGeld()
	{
		int startgeld = 100;

		Spiel spiel = SpielImplTest.getDefaultSpiel();

		Spieler s1 = SpielerImplTest.getDefaultSpieler(startgeld);
		FeldLos los = new FeldLos(new Einstellungen());
		los.betreteFeld(s1, 2, spiel);

		assertThat(s1.getRessourcenWert(Ressource.GELD), Is.is(new Einstellungen().getBetragBetretenLos() + startgeld));
	}

	public static Feld getDefaultFeld()
	{
		return new FeldLos(new Einstellungen());
	}
}
