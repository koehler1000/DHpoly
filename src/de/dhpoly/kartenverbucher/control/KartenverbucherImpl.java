package de.dhpoly.kartenverbucher.control;

import java.util.List;

import de.dhpoly.karte.control.BezahlKarte;
import de.dhpoly.karte.control.RueckenKarte;
import de.dhpoly.kartenverbucher.Kartenverbucher;
import de.dhpoly.spieler.Spieler;

public class KartenverbucherImpl implements Kartenverbucher
{
	public void bewegeGeld(BezahlKarte karte, List<Spieler> alleSpieler, Spieler ziehenderSpieler)
	{
		switch (karte.getTransfer())
		{
			case ANDERESPIELER_SPIELER:
				umbuchen(alleSpieler, ziehenderSpieler, karte.getGeldBetrag());
				break;
			case BANK_SPIELER:
				ziehenderSpieler.einzahlen(karte.getGeldBetrag());
				break;
			case SPIELER_ANDERESPIELER:
				umbuchen(ziehenderSpieler, alleSpieler, karte.getGeldBetrag());
				break;
			default:
				break;

		}
	}

	private static void umbuchen(List<Spieler> sender, Spieler empfaenger, int betrag)
	{
		for (Spieler send : sender)
		{
			umbuchen(send, empfaenger, betrag);
		}
	}

	private static void umbuchen(Spieler sender, List<Spieler> empfaenger, int betrag)
	{
		for (Spieler empf : empfaenger)
		{
			umbuchen(sender, empf, betrag);
		}
	}

	private static void umbuchen(Spieler sender, Spieler empfaenger, int betrag)
	{
		if (sender != empfaenger)
		{
			sender.auszahlen(betrag);
			empfaenger.einzahlen(betrag);
		}
	}

	@Override
	public void bewegeSpieler(RueckenKarte karte, Spieler spieler)
	{
		karte.getZiel().betreteFeld(spieler, 0);
	}
}
