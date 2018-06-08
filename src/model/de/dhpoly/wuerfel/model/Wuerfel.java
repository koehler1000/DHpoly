package de.dhpoly.wuerfel.model;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.wuerfel.view.WuerfelDatenUI;

public class Wuerfel extends Datenobjekt
{
	private static final long serialVersionUID = 1L;
	private int zahl = 1;

	public Wuerfel(int zahl)
	{
		this.zahl = zahl;
	}

	public void setZahl(int zahl)
	{
		this.zahl = zahl;
	}

	public int getZahl()
	{
		return zahl;
	}

	@Override
	public String getTitel()
	{
		return "Würfel";
	}

	@Override
	public Class<? extends Oberflaeche> getClassUI()
	{
		return WuerfelDatenUI.class;
	}
}
