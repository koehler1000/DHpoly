package de.dhpoly.handel.model;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.Feld;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.Spieler;

public class Transaktion extends Datenobjekt
{
	private static final long serialVersionUID = 1L;

	private transient List<Feld> felderEigentumswechsel = new ArrayList<>();

	private transient List<RessourcenDatensatz> ressourcenGeben = new ArrayList<>();
	private transient List<RessourcenDatensatz> ressourcenBekommen = new ArrayList<>();

	private transient Spieler anbietender;
	private transient Spieler handelspartner;

	private boolean veraendert = false;

	public Transaktion(Spieler anbietender, Spieler handelspartner)
	{
		this.anbietender = anbietender;
		this.handelspartner = handelspartner;
		this.veraendert = false;
	}

	public Transaktion getTransaktionsAngebot()
	{
		Transaktion t = new Transaktion(anbietender, handelspartner);
		t.felderEigentumswechsel = felderEigentumswechsel;
		t.ressourcenGeben = ressourcenGeben;
		t.ressourcenBekommen = ressourcenBekommen;
		t.veraendert = false;
		return t;
	}

	public void addDatensatzGeben(RessourcenDatensatz datensatz)
	{
		ressourcenGeben.add(datensatz);
		veraendert = true;
	}

	public void removeDatensatzGeben(RessourcenDatensatz datensatz)
	{
		ressourcenGeben.remove(datensatz);
		veraendert = true;
	}

	public void addDatensatzBekommen(RessourcenDatensatz datensatz)
	{
		ressourcenBekommen.add(datensatz);
		veraendert = true;
	}

	public void removeDatensatzBekommen(RessourcenDatensatz datensatz)
	{
		ressourcenBekommen.remove(datensatz);
		veraendert = true;
	}

	public void addDatensatzFelderwechsel(Feld feld)
	{
		felderEigentumswechsel.add(feld);
		veraendert = true;
	}

	public void removeDatensatzFelderwechsel(Feld feld)
	{
		felderEigentumswechsel.remove(feld);
		veraendert = true;
	}

	public boolean isVeraendert()
	{
		return veraendert;
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

	@Override
	public String getTitel()
	{
		return "Handel";
	}
}
