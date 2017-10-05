package de.dhpoly.kartenstapel.model;

public class Karte
{
	private String beschreibung;
	private GeldTransfer transfer;
	private int geldBetrag;

	public Karte(String beschreibung, GeldTransfer transfer, int geldBetrag)
	{
		super();
		this.beschreibung = beschreibung;
		this.transfer = transfer;
		this.geldBetrag = geldBetrag;
	}

	public String getBeschreibung()
	{
		return beschreibung;
	}

	public GeldTransfer getTransfer()
	{
		return transfer;
	}

	public int getGeldBetrag()
	{
		return geldBetrag;
	}

}
