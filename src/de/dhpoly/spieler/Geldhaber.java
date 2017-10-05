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
	
	public boolean isNegative()
	{
		return bargeld >= 0;
	}

}
