package de.dhpoly.spieler;

import de.dhpoly.player.Geldhaber;

public interface Spieler
{
	public int getBargeld();

	public void einzahlen(int betrag);

	public void auszahlen(int betrag);

	public void ueberweiseGeld(int betrag, Geldhaber empfaenger);

	public boolean isNegative();

	public int getFeldNr();

	public String getName();

	public void setFeldNr(int feldNrSoll);
}
