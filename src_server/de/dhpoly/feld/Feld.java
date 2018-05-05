package de.dhpoly.feld;

import java.util.List;

import de.dhpoly.logik.Logik;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.model.Spieler;

public interface Feld extends Logik
{
	public String getBeschriftung();

	public void betreteFeld(Spieler spieler, int augensumme, Spiel spiel);

	public void laufeUeberFeld(Spieler spieler);

	public void verlasseFeld(Spieler spieler);

	public List<Spieler> getSpielerAufFeld();

	public boolean gehoertSpieler(Spieler spielerDaten);

	public boolean isKaufbar();
}
