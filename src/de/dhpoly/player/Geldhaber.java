package de.dhpoly.player;

public interface Geldhaber
{
	public int getBargeld();

	public void einzahlen(int betrag);

	public void auszahlen(int betrag);

	public void ueberweiseGeld(int betrag, Geldhaber empfaenger);

	public boolean isNegative();
}
