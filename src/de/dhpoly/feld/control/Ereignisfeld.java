package de.dhpoly.feld.control;

import de.dhpoly.karte.Karte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.kartenstapel.Kartenstapel;
import de.dhpoly.spieler.Spieler;

public class Ereignisfeld extends FeldImpl
{
	private Kartenstapel kartenstapel;

	public Ereignisfeld(Kartenstapel kartenstapel)
	{
		super("Ereignisfeld");
		this.kartenstapel = kartenstapel;
	}

	@Override
	protected void spielerBetrittFeld(Spieler spieler, int augensumme, Wetter aktuellesWetter)
	{
		Karte karte = kartenstapel.ziehen();
		spieler.zeigeKarte(karte);
	}
}
