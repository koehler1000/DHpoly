package de.dhpoly.karte.view;

import de.dhpoly.karte.model.BezahlKarte;
import de.dhpoly.kartenstapel.model.BezahlZiel;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.utils.Spielansicht;

public class KarteUIVorschau
{
	public static void main(String[] args)
	{
		Spielansicht.zeige(new KarteUI(new BezahlKarte("Die Steuer wird f�llig, zahle 1000�", BezahlZiel.BANK,
				BezahlZiel.ALLE_SPIELER, new RessourcenDatensatz(Ressource.GELD, 1000)),
				Spielansicht.getSpielfeldAnsicht()));
	}
}
