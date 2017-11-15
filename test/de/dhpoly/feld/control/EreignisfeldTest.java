package de.dhpoly.feld.control;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.dhpoly.feld.Feld;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.karte.Karte;
import de.dhpoly.karte.control.BezahlKarte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.kartenstapel.control.KartenstapelImpl;
import de.dhpoly.kartenstapel.model.GeldTransfer;
import de.dhpoly.spieler.Spieler;

public class EreignisfeldTest
{
	private static boolean ereigniskarteGezeigt = false;

	@Test
	public void ereigniskarteWirdBeimBetretenGezeigt()
	{
		ereigniskarteGezeigt = false;

		Spieler spieler = new Spieler()
		{

			@Override
			public void zeigeTransaktionsvorschlag(Transaktion transaktion)
			{}

			@Override
			public void zeigeKaufmoeglichkeit(Strasse strasse)
			{}

			@Override
			public void zeigeKarte(Karte karte)
			{
				ereigniskarteGezeigt = true;

			}

			@Override
			public void ueberweiseGeld(int betrag, Spieler empfaenger)
			{}

			@Override
			public void setFeldNr(int feldNrSoll)
			{}

			@Override
			public boolean isNegative()
			{
				return false;
			}

			@Override
			public String getName()
			{
				return null;
			}

			@Override
			public int getFeldNr()
			{
				return 0;
			}

			@Override
			public int getBargeld()
			{
				return 0;
			}

			@Override
			public void einzahlen(int betrag)
			{}

			@Override
			public void auszahlen(int betrag)
			{}

			@Override
			public int getSteinVorrat()
			{
				return 0;
			}

			@Override
			public int getHolzVorrat()
			{
				return 0;
			}

			@Override
			public int getSpielerNr()
			{
				return 0;
			}

			@Override
			public void setAkutellerSpieler(boolean isAktuell)
			{}

			@Override
			public boolean isAktuellerSpieler()
			{
				return false;
			}
		};

		List<Karte> kartenstapel = new ArrayList<>();
		kartenstapel.add(new BezahlKarte("bla", GeldTransfer.ANDERESPIELER_SPIELER, 1000));

		Feld ereignisfeld = new Ereignisfeld(new KartenstapelImpl(kartenstapel));
		ereignisfeld.betreteFeld(spieler, 2, Wetter.BEWOELKT);

		assertTrue(ereigniskarteGezeigt);
	}
}
