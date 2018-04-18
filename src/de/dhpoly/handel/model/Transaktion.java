package de.dhpoly.handel.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.Feld;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.Spieler;

public class Transaktion extends Datenobjekt
{
	private static final long serialVersionUID = 1L;

	private transient List<Feld> felderEigentumswechsel = new ArrayList<>();

	private transient Map<Spieler, Map<Ressource, Integer>> ressourcen = new HashMap<>();

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
		t.ressourcen = ressourcen;
		t.veraendert = false;
		return t;
	}

	public int getRessource(Spieler abgebenderSpieler, Ressource ressource)
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
			Map<Ressource, Integer> map = new EnumMap<>(Ressource.class);
			ressourcen.put(abgebenderSpieler, map);
			setRessourcen(abgebenderSpieler, ressource, value);
		}
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

	@Override
	public String getTitel()
	{
		return "Handel";
	}
}
