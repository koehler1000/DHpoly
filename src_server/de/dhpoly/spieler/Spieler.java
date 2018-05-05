package de.dhpoly.spieler;

import de.dhpoly.logik.Logik;
import de.dhpoly.spieler.model.SpielerDaten;

public interface Spieler extends Logik
{
	SpielerDaten getDaten();
}
