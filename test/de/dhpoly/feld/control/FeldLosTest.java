package de.dhpoly.feld.control;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.feld.Feld;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImplTest;

public class FeldLosTest
{
	@Test
	public void spielerBetrittLosUndErhaeltEingestelltesGeld()
	{
		int startgeld = 100;

		Spieler s1 = SpielerImplTest.getDefaultSpieler(startgeld);
		FeldLos los = new FeldLos(new Einstellungen());
		los.betreteFeld(s1, 2, Wetter.BEWOELKT);

		assertThat(s1.getDaten().getRessourcenWert(Ressource.GELD),
				Is.is(new Einstellungen().getBetragBetretenLos() + startgeld));
	}

	public static Feld getDefaultFeld()
	{
		return new FeldLos(new Einstellungen());
	}
}
