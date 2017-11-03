package de.dhpoly.karte.control;

import de.dhpoly.karte.Karte;
import de.dhpoly.kartenstapel.model.GeldTransfer;

public class BezahlKarte implements Karte
{
	private String beschreibung;
	private GeldTransfer transfer;
	private int geldBetrag;

	public BezahlKarte(String beschreibung, GeldTransfer transfer, int geldBetrag)
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

	@Override
	public String getTitel()
	{
		return "Bezahlkarte";
	}

}
