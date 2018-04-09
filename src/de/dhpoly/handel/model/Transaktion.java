package de.dhpoly.handel.model;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.Spieler;

public class Transaktion
{
	private List<Feld> felderEigentumswechsel = new ArrayList<>();

	private List<RessourcenDatensatz> ressourcenGeben = new ArrayList<>();
	private List<RessourcenDatensatz> ressourcenBekommen = new ArrayList<>();

	private Spieler anbietender;
	private Spieler handelspartner;

	public Transaktion(Spieler anbietender, Spieler handelspartner)
	{
		this.anbietender = anbietender;
		this.handelspartner = handelspartner;
	}

	// TODO neuen Konstruktor verwenden und getter und setter verwenden.
	@Deprecated
	public Transaktion(List<Feld> felderEigentumswechsel, List<RessourcenDatensatz> ressourcenGeben,
			List<RessourcenDatensatz> ressourcenBekommen, Spieler anbietender, Spieler handelspartner)
	{
		this.felderEigentumswechsel.addAll(felderEigentumswechsel);
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

	public List<Feld> getFelderEigentumswechsel()
	{
		return felderEigentumswechsel;
	}

	public Spieler getAnbietender()
	{
		return anbietender;
	}

	public Spieler getHandelspartner()
	{
		return handelspartner;
	}

	public boolean isFeldInEigentumswechsel(Feld feld)
	{
		return felderEigentumswechsel.contains(feld);
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
		for (Feld feld : felderEigentumswechsel)
		{
			if (!transaktion.getFelderEigentumswechsel().contains(feld))
			{
				return false;
			}
		}
		for (Feld feld : transaktion.getFelderEigentumswechsel())
		{
			if (!getFelderEigentumswechsel().contains(feld))
			{
				return false;
			}
		}

		return true;
	}
}
