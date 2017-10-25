package de.dhpoly.kartenstapel.control;

import java.util.List;

import de.dhpoly.kartenstapel.Kartenverbucher;
import de.dhpoly.kartenstapel.model.Karte;
import de.dhpoly.spieler.Spieler;

public class KartenverbucherImpl implements Kartenverbucher
{

	public void bewegeGeld(Karte karte, List<Spieler> alleSpieler, Spieler ziehenderSpieler, Spieler freiparken)
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
			case SPIELER_FREIPARKEN:
				umbuchen(ziehenderSpieler, freiparken, karte.getGeldBetrag());
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
}
