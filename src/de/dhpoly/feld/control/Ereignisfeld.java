package de.dhpoly.feld.control;

import de.dhpoly.feld.Feld;
import de.dhpoly.karte.Karte;
import de.dhpoly.kartenstapel.Kartenstapel;
import de.dhpoly.spieler.Spieler;

public class Ereignisfeld implements Feld
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
	public void betreteFeld(Spieler spieler, int augensumme)
	{
		Karte karte = kartenstapel.ziehen();
		spieler.zeigeKarte(karte);
	}

}
