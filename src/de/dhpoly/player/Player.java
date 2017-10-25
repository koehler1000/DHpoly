package de.dhpoly.player;

import de.dhpoly.spieler.model.GeldhaberImpl;

public class Player
{
	private int feldNr = 0;
	private String name;
	private GeldhaberImpl kasse;

	public Player(String name, int startguthaben)
	{
		this.name = name;
		kasse = new GeldhaberImpl(startguthaben);
	}

	public int getFeldNr()
	{
		return feldNr;
	}

	public String getName()
	{
		return name;
	}

	public GeldhaberImpl getKasse()
	{
		return kasse;
	}

	public void setFeldNr(int feldNrSoll)
	{
		this.feldNr = feldNrSoll;
	}

}
