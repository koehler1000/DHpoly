package de.dhpoly.kartenverbucher.control;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.karte.model.BezahlKarte;
import de.dhpoly.karte.model.RueckenKarte;
import de.dhpoly.kartenstapel.model.BezahlZiel;
import de.dhpoly.kartenverbucher.Kartenverbucher;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.model.Spieler;

public class KartenverbucherImpl implements Kartenverbucher
{
	public void bewegeGeld(BezahlKarte karte, List<Spieler> alleSpieler, Spieler ziehenderSpieler)
	{
		List<Spieler> andereSpieler = new ArrayList<>();
		alleSpieler.forEach(andereSpieler::add);
		andereSpieler.remove(ziehenderSpieler);

		List<Spieler> bezahlQuelle = new ArrayList<>();
		List<Spieler> bezahlZiel = new ArrayList<>();

		switch (karte.getGeldQuelle())
		{
			case SPIELER_ANDERE:
				bezahlQuelle = andereSpieler;
				break;
			case SPIELER_ZIEHER:
				bezahlQuelle.add(ziehenderSpieler);
				break;
			case ALLE_SPIELER:
				bezahlQuelle = alleSpieler;
				break;
			case BANK:
				break;
		}

		switch (karte.getGeldZiel())
		{
			case SPIELER_ANDERE:
				bezahlZiel = andereSpieler;
				break;
			case SPIELER_ZIEHER:
				bezahlZiel.add(ziehenderSpieler);
				break;
			case ALLE_SPIELER:
				bezahlZiel = alleSpieler;
				break;
			case BANK:
				ueberweiseAnBank(bezahlQuelle, karte.getRessourcenDatensaetze());
				break;
		}
		if (karte.getGeldQuelle() != BezahlZiel.BANK)
		{
			ueberweise(bezahlQuelle, bezahlZiel, karte.getRessourcenDatensaetze());
		}
		else
		{
			ueberweiseVonBank(bezahlZiel, karte.getRessourcenDatensaetze());
		}

	}

	private void ueberweise(List<Spieler> spielerQuelle, List<Spieler> spielerZiel, List<RessourcenDatensatz> ressource)
	{
		for (Spieler spielerQ : spielerQuelle)
		{
			for (Spieler spielerZ : spielerZiel)
			{

				spielerQ.auszahlen(ressource);
				spielerZ.einzahlen(ressource);
			}
		}
	}

	private void ueberweiseAnBank(List<Spieler> spieler, List<RessourcenDatensatz> ressource)
	{
		for (Spieler spielerQ : spieler)
		{
			spielerQ.auszahlen(ressource);
		}
	}

	private void ueberweiseVonBank(List<Spieler> spieler, List<RessourcenDatensatz> ressource)
	{
		for (Spieler spielerZ : spieler)
		{
			spielerZ.einzahlen(ressource);
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
