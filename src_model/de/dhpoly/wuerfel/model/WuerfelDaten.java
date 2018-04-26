package de.dhpoly.wuerfel.model;

import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;

public class WuerfelDaten extends Datenobjekt
{
	private static final long serialVersionUID = 1L;

	private List<Wuerfel> wuerfel;

	public WuerfelDaten(List<Wuerfel> wuerfel)
	{
		this.wuerfel = wuerfel;
	}

	public final List<Wuerfel> getWuerfel()
	{
		return wuerfel;
	}

	@Override
	public String getTitel()
	{
		return "Wuerfel";
	}
}
