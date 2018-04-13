package de.dhpoly.wuerfel.control;

import de.dhpoly.datenobjekt.Datenobjekt;

public class Wuerfel extends Datenobjekt
{
	private static final long serialVersionUID = 1L;
	int zahl = 1;

	public Wuerfel(int zahl)
	{
		this.zahl = zahl;
	}

	public void setZahl(int zahl)
	{
		this.zahl = zahl;
		informiereBeobachter();
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
}
