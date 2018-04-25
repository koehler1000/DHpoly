package de.dhpoly.kartenverbucher.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.karte.model.BezahlKarte;
import de.dhpoly.karte.model.RueckenKarte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.kartenverbucher.Kartenverbucher;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;

public class KartenverbucherImpl implements Kartenverbucher
{
	public void bewegeGeld(BezahlKarte karte, List<Spieler> alleSpieler, Spieler ziehenderSpieler)
	{
		List<Spieler> ziehenderSpielerAlsListe = new ArrayList<>();
		ziehenderSpielerAlsListe.add(ziehenderSpieler);

		switch (karte.getTransfer())
		{
			case ANDERESPIELER_SPIELER:
				umbuchen(alleSpieler, ziehenderSpielerAlsListe, karte.getRessourcenDatensaetze());
				break;
			case BANK_SPIELER:
				ziehenderSpieler.einzahlen(karte.getRessourcenDatensaetze());
				break;
			case SPIELER_ANDERESPIELER:
				umbuchen(ziehenderSpielerAlsListe, alleSpieler, karte.getRessourcenDatensaetze());
				break;
			default:
				break;
		}
	}

	private void umbuchen(List<Spieler> sender, List<Spieler> empfaenger, List<RessourcenDatensatz> datensaetze)
	{
		for (Spieler spielerSender : sender)
		{
			for (Spieler spielerEmpfaenger : empfaenger)
			{
				spielerEmpfaenger.einzahlen(datensaetze);
				spielerSender.auszahlen(datensaetze);
			}
		}
	}

	@Override
	public void bewegeSpieler(RueckenKarte karte, Spieler spieler, Wetter wetter)
	{
		karte.getZiel().betreteFeld(spieler, 0, wetter);
	}

	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel, Spieler spieler) throws IOException
	{
		if (objekt instanceof BezahlKarte)
		{
			BezahlKarte karte = (BezahlKarte) objekt;
			bewegeGeld(karte, spiel.getSpieler(), spieler);
		}
		else if (objekt instanceof RueckenKarte)
		{
			RueckenKarte karte = (RueckenKarte) objekt;
			bewegeSpieler(karte, spieler, spiel.getWetter());
		}
	}
}
