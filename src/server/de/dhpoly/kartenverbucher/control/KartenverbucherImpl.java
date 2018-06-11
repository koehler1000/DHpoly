package de.dhpoly.kartenverbucher.control;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.karte.model.BezahlKarte;
import de.dhpoly.karte.model.RueckenKarte;
import de.dhpoly.kartenverbucher.Kartenverbucher;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.model.Spieler;

public class KartenverbucherImpl implements Kartenverbucher
{
	public void bewegeGeld(BezahlKarte karte, List<Spieler> alleSpieler, Spieler ziehenderSpieler)
	{
		List<Spieler> andereSpieler = new ArrayList<>();
		alleSpieler.forEach(andereSpieler::add);
		andereSpieler.remove(ziehenderSpieler);

		switch (karte.getGeldQuelle())
		{
			case SPIELER_ANDERE:
				andereSpieler.forEach(e -> e.auszahlen(karte.getRessourcenDatensaetze()));
				break;
			case SPIELER_ZIEHER:
				ziehenderSpieler.auszahlen(karte.getRessourcenDatensaetze());
				break;
			case ALLE_SPIELER:
				alleSpieler.forEach(e -> e.auszahlen(karte.getRessourcenDatensaetze()));
				break;
			default:
				break;
		}

		switch (karte.getGeldZiel())
		{
			case SPIELER_ANDERE:
				andereSpieler.forEach(e -> e.einzahlen(karte.getRessourcenDatensaetze()));
				break;
			case SPIELER_ZIEHER:
				ziehenderSpieler.einzahlen(karte.getRessourcenDatensaetze());
				break;
			case ALLE_SPIELER:
				alleSpieler.forEach(e -> e.einzahlen(karte.getRessourcenDatensaetze()));
				break;
			default:
				break;
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
