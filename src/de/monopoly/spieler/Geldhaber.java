package de.monopoly.spieler;

public class Geldhaber
{
	public Geldhaber(int bargeld)
	{
		super();
		this.bargeld = bargeld;
	}

	private int bargeld;

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
