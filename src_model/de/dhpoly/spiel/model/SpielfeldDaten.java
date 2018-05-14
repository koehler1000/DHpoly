package de.dhpoly.spiel.model;

import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.Feld;
import de.dhpoly.oberflaeche.view.Oberflaeche;

public class SpielfeldDaten extends Datenobjekt
{
	private static final long serialVersionUID = 1L;
	private List<Feld> felder;

	public SpielfeldDaten(List<Feld> felder)
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
		return null;
	}

	public final List<Feld> getFelder()
	{
		return felder;
	}

	public Feld get(int idx)
	{
		return felder.get(idx);
	}

	public int indexOf(Feld aktuellesFeld)
	{
		return felder.indexOf(aktuellesFeld);
	}

	public int size()
	{
		return felder.size();
	}
}
