package de.dhpoly.kartenverbucher.control;

import java.util.List;

import de.dhpoly.karte.control.BezahlKarte;
import de.dhpoly.karte.control.RueckenKarte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.kartenverbucher.Kartenverbucher;
import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.spieler.Spieler;

public class KartenverbucherImpl implements Kartenverbucher
{
	public void bewegeGeld(BezahlKarte karte, List<Spieler> alleSpieler, Spieler ziehenderSpieler)
	{
		switch (karte.getTransfer())
		{
			case ANDERESPIELER_SPIELER:
				umbuchen(alleSpieler, ziehenderSpieler, karte.getRessourcenDatensatz());
				break;
			case BANK_SPIELER:
				ziehenderSpieler.einzahlen(karte.getRessourcenDatensatz());
				break;
			case SPIELER_ANDERESPIELER:
				umbuchen(ziehenderSpieler, alleSpieler, karte.getRessourcenDatensatz());
				break;
			default:
				break;

		}
	}

	private void umbuchen(Spieler ziehenderSpieler, List<Spieler> alleSpieler, RessourcenDatensatz ressourcenDatensatz)
	{
		for (Spieler empf : alleSpieler)
		{
			umbuchen(ziehenderSpieler, empf, ressourcenDatensatz);
		}
	}

	private static void umbuchen(List<Spieler> sender, Spieler empfaenger, RessourcenDatensatz datensatz)
	{
		for (Spieler send : sender)
		{
			umbuchen(send, empfaenger, datensatz);
		}
	}

	// private static void umbuchen(Spieler sender, List<Spieler> empfaenger, int
	// betrag)
	// {
	// for (Spieler empf : empfaenger)
	// {
	// umbuchen(sender, empf, betrag);
	// }
	// }

	private static void umbuchen(Spieler sender, Spieler empfaenger, RessourcenDatensatz datensatz)
	{
		if (sender != empfaenger)
		{
			sender.auszahlen(datensatz);
			empfaenger.einzahlen(datensatz);
		}
	}

	@Override
	public void bewegeSpieler(RueckenKarte karte, Spieler spieler, Wetter wetter)
	{
		karte.getZiel().betreteFeld(spieler, 0, wetter);
	}
}
