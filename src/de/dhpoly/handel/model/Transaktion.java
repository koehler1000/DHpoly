package de.dhpoly.handel.model;

import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.Spieler;

public class Transaktion
{
	private List<Feld> felderGeben;
	private List<Feld> felderBekommen;

	private List<RessourcenDatensatz> ressourcenGeben;
	private List<RessourcenDatensatz> ressourcenBekommen;

	private Spieler anbietender;
	private Spieler handelspartner;

	public Transaktion(List<Feld> felderGeben, List<Feld> felderBekommen, List<RessourcenDatensatz> ressourcenGeben,
			List<RessourcenDatensatz> ressourcenBekommen, Spieler anbietender, Spieler handelspartner)
	{
		super();
		this.felderGeben = felderGeben;
		this.felderBekommen = felderBekommen;
		this.ressourcenGeben = ressourcenGeben;
		this.ressourcenBekommen = ressourcenBekommen;
		this.anbietender = anbietender;
		this.handelspartner = handelspartner;
	}

	public List<RessourcenDatensatz> getRessourcenGeben()
	{
		return ressourcenGeben;
	}

	public List<RessourcenDatensatz> getRessourcenBekommen()
	{
		return ressourcenBekommen;
	}

	public List<Feld> getFelderGeben()
	{
		return felderGeben;
	}

	public List<Feld> getFelderBekommen()
	{
		return felderBekommen;
	}

	public Spieler getAnbietender()
	{
		return anbietender;
	}

	public Spieler getHandelspartner()
	{
		return handelspartner;
	}

	public boolean isFeldInBekommen(Feld feld)
	{
		return felderBekommen.contains(feld);
	}

	public boolean isFeldInGeben(Feld feld)
	{
		return felderGeben.contains(feld);
	}

	public int getWertBekommen(Ressource ressource)
	{
		int anz = 0;
		for (RessourcenDatensatz daten : ressourcenBekommen)
		{
			if (daten.getRessource() == ressource)
			{
				anz += daten.getAnzahl();
			}
		}

		return anz;
	}

	public int getWertGeben(Ressource ressource)
	{
		int anz = 0;
		for (RessourcenDatensatz daten : ressourcenGeben)
		{
			if (daten.getRessource() == ressource)
			{
				anz += daten.getAnzahl();
			}
		}

		return anz;
	}

	public boolean isGleich(Transaktion transaktion)
	{
		// TODO
		return false;
	}
}
