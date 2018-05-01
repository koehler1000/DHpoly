package de.dhpoly.wuerfel.model;

import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.wuerfel.view.WuerfelUI;

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

	@Override
	public Class<? extends Oberflaeche> getClassUI()
	{
		return WuerfelUI.class;
	}
}
