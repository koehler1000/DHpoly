package de.dhpoly.karte.model;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.karte.view.KarteUI;
import de.dhpoly.kartenstapel.model.GeldTransfer;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.ressource.model.RessourcenDatensatz;

public class BezahlKarte extends Datenobjekt implements Karte
{
	private static final long serialVersionUID = 1L;

	private String beschreibung;
	private GeldTransfer transfer;
	private transient List<RessourcenDatensatz> ressourcenDatensaetze;

	public BezahlKarte(String beschreibung, GeldTransfer transfer, List<RessourcenDatensatz> ressourcenDatensaetze)
	{
		super();
		this.beschreibung = beschreibung;
		this.transfer = transfer;
		this.ressourcenDatensaetze = ressourcenDatensaetze;
	}

	public BezahlKarte(String beschreibung, GeldTransfer transfer, RessourcenDatensatz datensatz)
	{
		this(beschreibung, transfer, new ArrayList<>());
		ressourcenDatensaetze.add(datensatz);
	}

	public String getBeschreibung()
	{
		return beschreibung;
	}

	public GeldTransfer getTransfer()
	{
		return transfer;
	}

	public List<RessourcenDatensatz> getRessourcenDatensaetze()
	{
		return ressourcenDatensaetze;
	}

	@Override
	public String getTitel()
	{
		return "Bezahlkarte";
	}

	@Override
	public Class<? extends Oberflaeche> getClassUI()
	{
		return KarteUI.class;
	}
}
