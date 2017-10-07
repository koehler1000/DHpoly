package de.dhpoly.spieler;

public class Geldhaber
{
	private int bargeld;
	
	public Geldhaber(int bargeld)
	{
		this.bargeld = bargeld;
	}

	public int getBargeld()
	{
		return bargeld;
	}

	public void einzahlen(int betrag)
	{
		bargeld += betrag;
	}

	public void auszahlen(int betrag)
	{
		bargeld -= betrag;
	}
	
	public void ueberweiseGeld(int betrag, Geldhaber empfaenger)
	{
		empfaenger.einzahlen(betrag);
		this.auszahlen(betrag);
	}
	
	public boolean isNegative()
	{
		return bargeld >= 0;
	}

}