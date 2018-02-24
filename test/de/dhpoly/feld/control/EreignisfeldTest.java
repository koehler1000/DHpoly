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
import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.control.RessourcenDatensatzImpl;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.Spieler;
import observerpattern.Beobachter;

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

			@Override
			public void addBeobachterHinzu(Beobachter beobachter)
			{}

			@Override
			public void einzahlen(RessourcenDatensatz datensatz)
			{}

			@Override
			public void auszahlen(RessourcenDatensatz datensatz)
			{}

			@Override
			public void ueberweise(RessourcenDatensatz datensatz, Spieler empfaenger)
			{}

			@Override
			public List<RessourcenDatensatz> getRessourcenTransaktionen()
			{
				return null;
			}

			@Override
			public int getRessourcenWerte(Ressource ressource)
			{
				return 0;
			}

			@Override
			public void einzahlen(List<RessourcenDatensatz> datensaetze)
			{}

			@Override
			public void auszahlen(List<RessourcenDatensatz> datensaetze)
			{}

			@Override
			public boolean kannBezahlen(List<RessourcenDatensatz> kostenHaus)
			{
				return false;
			}

			@Override
			public void zeigeNachrichtVerloren()
			{}

			@Override
			public void zeigeNachrichtGewonnen()
			{}

			@Override
			public List<Feld> getFelder()
			{
				return null;
			}

			@Override
			public void feldHinzu(Feld feld)
			{}

			@Override
			public void feldWeg(Feld feld)
			{}

			@Override
			public void setSpielerNr(int nr)
			{}

			@Override
			public boolean hatVerloren()
			{
				return false;
			}
		};

		List<Karte> kartenstapel = new ArrayList<>();
		kartenstapel.add(new BezahlKarte("bla", GeldTransfer.ANDERESPIELER_SPIELER,
				new RessourcenDatensatzImpl(Ressource.GELD, 1000)));

		Feld ereignisfeld = new Ereignisfeld(new KartenstapelImpl(kartenstapel));
		ereignisfeld.betreteFeld(spieler, 2, Wetter.BEWOELKT);

		assertTrue(ereigniskarteGezeigt);
	}
}
