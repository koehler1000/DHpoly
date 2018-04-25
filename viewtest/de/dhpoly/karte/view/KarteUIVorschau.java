package de.dhpoly.karte.view;

import de.dhpoly.karte.model.BezahlKarte;
import de.dhpoly.kartenstapel.model.GeldTransfer;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.utils.Spielansicht;

public class KarteUIVorschau
{
	public static void main(String[] args)
	{
		Spielansicht.zeige(new KarteUI(new BezahlKarte("Die Steuer wird fällig, zahle 1000€", GeldTransfer.BANK_SPIELER,
				new RessourcenDatensatz(Ressource.GELD, 1000)), Spielansicht.getSpielfeldAnsicht()));
	}
}
