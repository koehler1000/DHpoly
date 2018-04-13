package de.dhpoly.wuerfel.control;

import observerpattern.Beobachtbarer;

public class Wuerfel extends Beobachtbarer
{
	int zahl = 1;

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
}
