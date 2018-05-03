package de.dhpoly.spieler.model;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.Spieler;

public class SpielerDaten extends Datenobjekt
{
	private static final long serialVersionUID = 1L;

	private SpielerTyp typ;
	private String name;
	private List<RessourcenDatensatz> kasse = new ArrayList<>();

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
		// TODO Auto-generated method stub
		return null;
	}

	public int getRessourcenWert(Ressource ressource)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Deprecated
	public Spieler getSpielerNr()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
