package de.dhpoly.feld.control;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.dhpoly.feld.Feld;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.karte.Karte;
import de.dhpoly.karte.control.BezahlKarte;
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
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void zeigeKaufmoeglichkeit(Strasse strasse)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void zeigeKarte(Karte karte)
			{
				ereigniskarteGezeigt = true;
			}

			@Override
			public void ueberweiseGeld(int betrag, Spieler empfaenger)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void setFeldNr(int feldNrSoll)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public boolean isNegative()
			{
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public String getName()
			{
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getFeldNr()
			{
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getBargeld()
			{
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public void einzahlen(int betrag)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void auszahlen(int betrag)
			{
				// TODO Auto-generated method stub

			}
		};

		List<Karte> kartenstapel = new ArrayList<>();
		kartenstapel.add(new BezahlKarte("bla", GeldTransfer.ANDERESPIELER_SPIELER, 1000));

		Feld ereignisfeld = new Ereignisfeld(new KartenstapelImpl(kartenstapel));
		ereignisfeld.betreteFeld(spieler, 2);

		assertTrue(ereigniskarteGezeigt);
	}

}
