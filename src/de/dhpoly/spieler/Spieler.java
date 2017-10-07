package de.dhpoly.spieler;

public class Spieler
{
	private int feldNr = 0;
	private String name;
	private Geldhaber kasse;

	public Spieler(String name, int startguthaben)
	{
		this.name = name;
		kasse = new Geldhaber(startguthaben);
	}

	public int getFeldNr()
	{
		return feldNr;
	}

	public String getName()
	{
		return name;
	}

	public Geldhaber getKasse()
	{
		return kasse;
	}

	public void setFeldNr(int feldNrSoll)
	{
		this.feldNr = feldNrSoll;
	}

}
