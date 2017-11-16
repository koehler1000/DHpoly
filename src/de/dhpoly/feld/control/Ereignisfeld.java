package de.dhpoly.feld.control;

import de.dhpoly.feld.Feld;
import de.dhpoly.karte.Karte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.kartenstapel.Kartenstapel;
import de.dhpoly.spieler.Spieler;

public class Ereignisfeld extends Feld
{
	private Kartenstapel kartenstapel;

	public Ereignisfeld(Kartenstapel kartenstapel)
	{
		this.kartenstapel = kartenstapel;
	}

	@Override
	public String getBeschriftung()
	{
		return "Ereignisfeld";
	}

	@Override
	protected void spielerBetrittFeld(Spieler spieler, int augensumme, Wetter aktuellesWetter)
	{
		Karte karte = kartenstapel.ziehen();
		spieler.zeigeKarte(karte);
	}
}
