package de.dhpoly.handel.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.Feld;
import de.dhpoly.handel.view.HandelUI;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.model.SpielerDaten;

public class Transaktion extends Datenobjekt
{
	private static final long serialVersionUID = 1L;

	// TODO Feld > FeldDaten
	private transient List<Feld> felderEigentumswechsel = new ArrayList<>();

	private Map<SpielerDaten, Map<Ressource, Integer>> ressourcen = new HashMap<>();

	private SpielerDaten anbietender;
	private SpielerDaten handelspartner;

	private TransaktionsTyp typ = TransaktionsTyp.NEU;

	public Transaktion(SpielerDaten anbietender, SpielerDaten handelspartner)
	{
		this.anbietender = anbietender;
		this.handelspartner = handelspartner;
		this.typ = TransaktionsTyp.NEU;
	}

	public Transaktion getTransaktionsGegenangebot()
	{
		Transaktion t = new Transaktion(handelspartner, anbietender);
		t.felderEigentumswechsel = felderEigentumswechsel;
		t.ressourcen = ressourcen;
		t.typ = TransaktionsTyp.VORSCHLAG;
		return t;
	}

	public int getRessource(SpielerDaten abgebenderSpieler, Ressource ressource)
	{
		Map<Ressource, Integer> res = ressourcen.getOrDefault(abgebenderSpieler, new HashMap<>());
		Integer wert = res.getOrDefault(ressource, Integer.valueOf(0));
		return wert.intValue();
	}

	public void setRessourcen(SpielerDaten abgebenderSpieler, Ressource ressource, int value)
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
			typ = TransaktionsTyp.NEUER_VORSCHLAG;
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
		typ = TransaktionsTyp.NEUER_VORSCHLAG;
	}

	public void removeDatensatzFelderwechsel(Feld feld)
	{
		felderEigentumswechsel.remove(feld);
		typ = TransaktionsTyp.NEUER_VORSCHLAG;
	}

	public TransaktionsTyp getTransaktionsTyp()
	{
		return typ;
	}

	public List<Feld> getFelderEigentumswechsel(SpielerDaten spielerDaten)
	{
		return getFelderEigentumswechsel().stream().filter(e -> e.gehoertSpieler(spielerDaten))
				.collect(Collectors.toList());
	}

	public List<Feld> getFelderEigentumswechsel()
	{
		return felderEigentumswechsel;
	}

	public SpielerDaten getAnbietender()
	{
		return anbietender;
	}

	public SpielerDaten getHandelspartner()
	{
		return handelspartner;
	}

	@Override
	public String getTitel()
	{
		return "Handel";
	}

	public void setTransaktionsTyp(TransaktionsTyp typ)
	{
		this.typ = typ;
	}

	@Override
	public Class<? extends Oberflaeche> getClassUI()
	{
		return HandelUI.class;
	}
}
