package de.dhpoly.handel.model;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.Spieler;

public class Transaktion
{
	private List<Feld> felderGeben = new ArrayList<>();
	private List<Feld> felderBekommen = new ArrayList<>();

	private List<RessourcenDatensatz> ressourcenGeben;
	private List<RessourcenDatensatz> ressourcenBekommen;

	private Spieler anbietender;
	private Spieler handelspartner;

	public Transaktion(List<Feld> felderGeben, List<Feld> felderBekommen, List<RessourcenDatensatz> ressourcenGeben,
			List<RessourcenDatensatz> ressourcenBekommen, Spieler anbietender, Spieler handelspartner)
	{
		super();
		this.felderGeben.addAll(felderGeben);
		this.felderBekommen.addAll(felderBekommen);
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
		// alle Ressourcen müssen beim Geben und Nehmen gleich sein
		for (Ressource res : Ressource.values())
		{
			if (this.getWertBekommen(res) != transaktion.getWertBekommen(res))
			{
				return false;
			}
			if (this.getWertGeben(res) != transaktion.getWertGeben(res))
			{
				return false;
			}
		}

		// alle Strassen müssen gleich sein
		for (Feld feld : felderBekommen)
		{
			if (!transaktion.getFelderBekommen().contains(feld))
			{
				return false;
			}
		}
		for (Feld feld : felderGeben)
		{
			if (!transaktion.getFelderGeben().contains(feld))
			{
				return false;
			}
		}

		System.out.println(transaktion.getFelderBekommen().size() + "|" + getFelderBekommen().size());
		System.out.println(transaktion.getFelderGeben().size() + "|" + getFelderGeben().size());

		for (Feld feld : transaktion.getFelderBekommen())
		{
			if (!getFelderBekommen().contains(feld))
			{
				return false;
			}
		}
		for (Feld feld : transaktion.getFelderGeben())
		{
			if (!getFelderGeben().contains(feld))
			{
				return false;
			}
		}

		return true;
	}
}
