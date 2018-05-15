package de.dhpoly.spiel.model;

import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.model.FeldDaten;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.spielfeld.view.SpielfeldUI;

public class SpielfeldDaten extends Datenobjekt
{
	private static final long serialVersionUID = 1L;
	private List<FeldDaten> felder;

	public SpielfeldDaten(List<FeldDaten> felder)
	{
		this.felder = felder;
	}

	@Override
	public String getTitel()
	{
		return "Spielfeld";
	}

	@Override
	public Class<? extends Oberflaeche> getClassUI()
	{
		return SpielfeldUI.class;
	}

	public List<FeldDaten> getFelder()
	{
		return felder;
	}

	public FeldDaten get(int idx)
	{
		return felder.get(idx);
	}

	public int indexOf(FeldDaten aktuellesFeld)
	{
		return felder.indexOf(aktuellesFeld);
	}

	public int size()
	{
		return felder.size();
	}
}
