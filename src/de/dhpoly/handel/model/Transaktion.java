package de.dhpoly.handel.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

	private Map<Spieler, Map<Ressource, Integer>> ressourcen = new HashMap<>();

	private transient Spieler anbietender;
	private transient Spieler handelspartner;

	private boolean veraendert = false;

	public Transaktion(Spieler anbietender, Spieler handelspartner)
	{
		this.anbietender = anbietender;
		this.handelspartner = handelspartner;
		this.veraendert = false;
	}

	public Transaktion getTransaktionsGegenangebot()
	{
		Transaktion t = new Transaktion(handelspartner, anbietender);
		t.felderEigentumswechsel = felderEigentumswechsel;
		t.ressourcenGeben = ressourcenBekommen;
		t.ressourcenBekommen = ressourcenGeben;
		t.veraendert = false;
		return t;
	}

	public int getRessourcen(Spieler abgebenderSpieler, Ressource ressource)
	{
		Map<Ressource, Integer> res = ressourcen.getOrDefault(abgebenderSpieler, new HashMap<>());
		Integer wert = res.getOrDefault(ressource, Integer.valueOf(0));
		return wert.intValue();
	}

	public void setRessourcen(Spieler abgebenderSpieler, Ressource ressource, int value)
	{
		if (ressourcen.containsKey(abgebenderSpieler))
		{
			Map<Ressource, Integer> res = ressourcen.get(abgebenderSpieler);
			res.put(ressource, value);
			veraendert = true;
		}
		else
		{
			Map<Ressource, Integer> map = new HashMap<>();
			ressourcen.put(abgebenderSpieler, map);
			setRessourcen(abgebenderSpieler, ressource, value);
		}
	}

	@Deprecated
	public void addDatensatzGeben(RessourcenDatensatz datensatz)
	{
		ressourcenGeben.add(datensatz);
		veraendert = true;
	}

	@Deprecated
	public void removeDatensatzGeben(RessourcenDatensatz datensatz)
	{
		ressourcenGeben.remove(datensatz);
		veraendert = true;
	}

	@Deprecated
	public void addDatensatzBekommen(RessourcenDatensatz datensatz)
	{
		ressourcenBekommen.add(datensatz);
		veraendert = true;
	}

	@Deprecated
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

	@Deprecated
	public List<RessourcenDatensatz> getRessourcenGeben()
	{
		return ressourcenGeben;
	}

	@Deprecated
	public List<RessourcenDatensatz> getRessourcenBekommen()
	{
		return ressourcenBekommen;
	}

	public List<Feld> getFelderEigentumswechsel(Spieler spieler)
	{
		return getFelderEigentumswechsel().stream().filter(e -> e.gehoertSpieler(spieler)).collect(Collectors.toList());
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

	@Deprecated
	public int getWert(Ressource ressource, Spieler spieler)
	{
		if (spieler == anbietender)
		{
			return getWertGeben(ressource);
		}
		if (spieler == handelspartner)
		{
			return getWertBekommen(ressource);
		}
		return 0;
	}

	@Deprecated
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

	@Deprecated
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

	@Deprecated
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
