package de.dhpoly.spieler.control;

import de.dhpoly.player.Geldhaber;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.model.GeldhaberImpl;

public class SpielerImpl implements Spieler
{
	private int feldNr = 0;
	private String name;
	private int bargeld;

	public SpielerImpl(String name, int startguthaben)
	{
		this.name = name;
		bargeld = startguthaben;
	}

	public int getFeldNr()
	{
		return feldNr;
	}

	public String getName()
	{
		return name;
	}

	public void setFeldNr(int feldNrSoll)
	{
		this.feldNr = feldNrSoll;
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
