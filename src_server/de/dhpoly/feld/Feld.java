package de.dhpoly.feld;

import java.util.List;

import de.dhpoly.karte.model.Wetter;
import de.dhpoly.spieler.Spieler;

public interface Feld // TODO extends Logik
{
	public String getBeschriftung();

	public void betreteFeld(Spieler spieler, int augensumme, Wetter aktuellesWetter);

	public void laufeUeberFeld(Spieler spieler);

	public void verlasseFeld(Spieler spieler);

	public List<Spieler> getSpielerAufFeld();

	public boolean gehoertSpieler(Spieler spieler);

	public boolean isKaufbar();
}
