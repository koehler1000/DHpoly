package de.dhpoly.karte.control;

import de.dhpoly.karte.Karte;
import de.dhpoly.kartenstapel.model.GeldTransfer;
import de.dhpoly.ressource.RessourcenDatensatz;

public class BezahlKarte implements Karte
{
	private String beschreibung;
	private GeldTransfer transfer;
	private RessourcenDatensatz ressourcenDatensatz;

	public BezahlKarte(String beschreibung, GeldTransfer transfer, RessourcenDatensatz ressourcenDatensatz)
	{
		super();
		this.beschreibung = beschreibung;
		this.transfer = transfer;
		this.ressourcenDatensatz = ressourcenDatensatz;
	}

	public String getBeschreibung()
	{
		return beschreibung;
	}

	public GeldTransfer getTransfer()
	{
		return transfer;
	}

	public RessourcenDatensatz getRessourcenDatensatz()
	{
		return ressourcenDatensatz;
	}

	@Override
	public String getTitel()
	{
		return "Bezahlkarte";
	}

}
