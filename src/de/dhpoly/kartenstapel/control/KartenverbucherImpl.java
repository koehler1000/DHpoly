package de.dhpoly.kartenstapel.control;

import java.util.List;

import de.dhpoly.kartenstapel.Kartenverbucher;
import de.monopoly.kartenstapel.model.Karte;
import de.monopoly.spieler.Geldhaber;

public class KartenverbucherImpl implements Kartenverbucher
{

	public void bewegeGeld(Karte karte, List<Geldhaber> alleSpieler, Geldhaber ziehenderSpieler, Geldhaber freiparken)
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
	
	private static void umbuchen(List<Geldhaber> sender, Geldhaber empfaenger, int betrag)
	{
		for (Geldhaber send : sender )
		{
			umbuchen (send, empfaenger, betrag);
		}
	}
	
	private static void umbuchen(Geldhaber sender, List<Geldhaber> empfaenger, int betrag)
	{
		for (Geldhaber empf : empfaenger )
		{
			umbuchen (sender, empf, betrag);
		}
	}

	private static void umbuchen(Geldhaber sender, Geldhaber empfaenger, int betrag)
	{
		if (sender != empfaenger)
		{
			sender.auszahlen(betrag);
			empfaenger.einzahlen(betrag);
		}
	}
}
