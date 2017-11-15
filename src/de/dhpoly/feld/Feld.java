package de.dhpoly.feld;

import java.util.List;

import de.dhpoly.karte.model.Wetter;
import de.dhpoly.spieler.Spieler;

public interface Feld
{
	public String getBeschriftung();

	public void betreteFeld(Spieler spieler, int augensumme, Wetter aktuellesWetter);

	public void verlasseFeld(Spieler spieler);

	public List<Spieler> getSpielerAufFeld();

	public default boolean gehoertSpieler(Spieler spieler)
	{
		return false;
	}

	public default boolean isKaufbar()
	{
		return false;
	}
}
