package de.dhpoly.feld.control;

import de.dhpoly.karte.model.Karte;
import de.dhpoly.kartenstapel.Kartenstapel;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.model.SpielerDaten;

public class FeldEreignis extends FeldImpl
{
	private Kartenstapel kartenstapel;

	public FeldEreignis(Kartenstapel kartenstapel)
	{
		super("Ereignisfeld");
		this.kartenstapel = kartenstapel;
	}

	@Override
	protected void spielerBetrittFeld(Spieler spieler, int augensumme, Spiel spiel)
	{
		Karte karte = kartenstapel.ziehen();
		spiel.verarbeiteKarte(karte);
	}

	@Override
	public boolean gehoertSpieler(SpielerDaten spielerDaten)
	{
		return false;
	}

	@Override
	public boolean gehoertSpieler(Spieler spieler)
	{
		return false;
	}
}
