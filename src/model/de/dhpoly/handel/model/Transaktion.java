package de.dhpoly.handel.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.dhpoly.datenobjekt.DatenobjektAnClient;
import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.handel.view.HandelUI;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.model.Spieler;

public class Transaktion extends DatenobjektAnClient
{
	private static final long serialVersionUID = 1L;

	private List<StrasseDaten> felderEigentumswechsel = new ArrayList<>();

	private Map<Spieler, Map<Ressource, Integer>> ressourcen = new HashMap<>();

	private Spieler anbietender;
	private Spieler handelspartner;

	private boolean isAnbietenderOK = true;
	private boolean isHandelspartnerOK = true;

	private boolean veraendert = false;

	public Transaktion(Spieler anbietender, Spieler handelspartner)
	{
		this.anbietender = anbietender;
		this.handelspartner = handelspartner;
	}

	public int getRessource(Spieler abgebenderSpieler, Ressource ressource)
	{
		Map<Ressource, Integer> res = ressourcen.getOrDefault(abgebenderSpieler, new HashMap<>());
		Integer wert = res.getOrDefault(ressource, Integer.valueOf(0));
		return wert.intValue();
	}

	public void setRessourcen(Spieler abgebenderSpieler, Ressource ressource, int value)
	{
		int alterWert = getRessource(abgebenderSpieler, ressource);
		if (alterWert == value)
		{
			return;
		}

		if (ressourcen.containsKey(abgebenderSpieler))
		{
			Map<Ressource, Integer> res = ressourcen.get(abgebenderSpieler);
			res.put(ressource, value);
			veraendert();
		}
		else
		{
			Map<Ressource, Integer> map = new EnumMap<>(Ressource.class);
			ressourcen.put(abgebenderSpieler, map);
			setRessourcen(abgebenderSpieler, ressource, value);
		}
	}

	private void veraendert()
	{
		veraendert = true;
		isAnbietenderOK = false;
		isHandelspartnerOK = false;
	}

	public void addDatensatzFelderwechsel(StrasseDaten strasse)
	{
		felderEigentumswechsel.add(strasse);
		veraendert();
	}

	public void removeDatensatzFelderwechsel(StrasseDaten strasse)
	{
		felderEigentumswechsel.remove(strasse);
		veraendert();
	}

	public List<StrasseDaten> getFelderEigentumswechsel(Spieler spielerDaten)
	{
		return getFelderEigentumswechsel().stream().filter(e -> e.gehoertSpieler(spielerDaten))
				.collect(Collectors.toList());
	}

	public List<StrasseDaten> getFelderEigentumswechsel()
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

	@Override
	public String getTitel()
	{
		return "Handel";
	}

	public List<Spieler> nichtEinverstandeneSpieler()
	{
		List<Spieler> spieler = new ArrayList<>();
		if (!isAnbietenderOK)
		{
			spieler.add(getAnbietender());
		}
		if (!isHandelspartnerOK)
		{
			spieler.add(getHandelspartner());
		}
		return spieler;
	}

	public boolean isVeraendert()
	{
		return veraendert;
	}

	@Override
	public Class<? extends Oberflaeche> getClassUI()
	{
		return HandelUI.class;
	}

	public void setEinverstanden(Spieler spieler)
	{
		if (spieler == handelspartner)
		{
			isHandelspartnerOK = true;
		}
		if (spieler == anbietender)
		{
			isAnbietenderOK = true;
		}
	}
}
