package de.dhpoly.karte.control;

import de.dhpoly.karte.Karte;
import de.dhpoly.kartenstapel.model.GeldTransfer;
import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.control.RessourcenDatensatzImpl;
import de.dhpoly.ressource.model.Ressource;

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

	@Deprecated
	public BezahlKarte(String beschreibung, GeldTransfer transfer, int geldbetrag)
	{
		super();
		this.beschreibung = beschreibung;
		this.transfer = transfer;
		this.ressourcenDatensatz = new RessourcenDatensatzImpl(Ressource.GELD, geldbetrag);
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
