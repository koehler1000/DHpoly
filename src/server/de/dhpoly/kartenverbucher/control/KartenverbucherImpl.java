package de.dhpoly.kartenverbucher.control;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.karte.model.BezahlKarte;
import de.dhpoly.karte.model.RueckenKarte;
import de.dhpoly.kartenverbucher.Kartenverbucher;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.model.Spieler;

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
	public void bewegeSpieler(RueckenKarte karte, Spieler spieler, Spiel spiel)
	{
		spiel.bewege(karte.getFelder(), spieler);
	}

	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel)
	{
		if (objekt instanceof BezahlKarte)
		{
			BezahlKarte karte = (BezahlKarte) objekt;
			bewegeGeld(karte, spiel.getSpieler(), spiel.getAktuellerSpieler());
		}
		else if (objekt instanceof RueckenKarte)
		{
			RueckenKarte karte = (RueckenKarte) objekt;
			bewegeSpieler(karte, spiel.getAktuellerSpieler(), spiel);
		}
	}
}
