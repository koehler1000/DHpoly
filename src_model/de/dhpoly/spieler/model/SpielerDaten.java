package de.dhpoly.spieler.model;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.view.SpielerUI;

public class SpielerDaten extends Datenobjekt
{
	private static final long serialVersionUID = 1L;

	private SpielerTyp typ;
	private String name;
	private List<RessourcenDatensatz> kasse = new ArrayList<>();
	private int spielerNr;

	public SpielerDaten(SpielerTyp typ, String name)
	{
		super();
		this.typ = typ;
		this.name = name;
	}

	public SpielerTyp getTyp()
	{
		return typ;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public String getTitel()
	{
		return "Spielerdaten";
	}

	@Override
	public Class<? extends Oberflaeche> getClassUI()
	{
		return SpielerUI.class;
	}

	public int getRessourcenWert(Ressource ressource)
	{
		int wert = 0;
		for (RessourcenDatensatz ressourcenDatensatz : kasse)
		{
			if (ressourcenDatensatz.getRessource() == ressource)
			{
				wert += ressourcenDatensatz.getAnzahl();
			}
		}
		return wert;
	}

	public int getSpielerNr()
	{
		return spielerNr;
	}

	public void setSpielerNr(int spielerNr)
	{
		this.spielerNr = spielerNr;
	}
}
