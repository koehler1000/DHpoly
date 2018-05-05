package de.dhpoly.feld;

import java.util.List;

import de.dhpoly.logik.Logik;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.model.SpielerDaten;

public interface Feld extends Logik
{
	public String getBeschriftung();

	public void betreteFeld(Spieler spieler, int augensumme, Spiel spiel);

	public void laufeUeberFeld(Spieler spieler);

	public void verlasseFeld(Spieler spieler);

	public List<Spieler> getSpielerAufFeld();

	public boolean gehoertSpieler(SpielerDaten spielerDaten);

	public boolean isKaufbar();
}
