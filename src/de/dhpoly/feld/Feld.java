package de.dhpoly.feld;

import java.util.List;

import de.dhpoly.karte.model.Wetter;
import de.dhpoly.spieler.Spieler;
import observerpattern.Beobachtbarer;

public abstract class Feld extends Beobachtbarer
{
	public abstract String getBeschriftung();

	public abstract void betreteFeld(Spieler spieler, int augensumme, Wetter aktuellesWetter);

	public abstract void verlasseFeld(Spieler spieler);

	public abstract List<Spieler> getSpielerAufFeld();

	public boolean gehoertSpieler(Spieler spieler)
	{
		return false;
	}

	public boolean isKaufbar()
	{
		return false;
	}
}
