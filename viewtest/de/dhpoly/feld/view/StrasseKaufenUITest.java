package de.dhpoly.feld.view;

import de.dhpoly.feld.control.Strasse;
import de.dhpoly.feld.control.StrasseTest;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.karte.Karte;
import de.dhpoly.spieler.Spieler;

public class StrasseKaufenUITest
{
	public static void main(String[] args)
	{
		new StrasseKaufenUI(StrasseTest.getDefaultStrasse(), new Spieler()
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
				// TODO Auto-generated method stub

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
			public void setAkutellerSpieler(boolean isAktuell)
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
			public boolean isAktuellerSpieler()
			{
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public int getSteinVorrat()
			{
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getSpielerNr()
			{
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String getName()
			{
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getHolzVorrat()
			{
				// TODO Auto-generated method stub
				return 0;
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
		});
	}
}
