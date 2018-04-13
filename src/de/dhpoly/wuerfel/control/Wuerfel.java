package de.dhpoly.wuerfel.control;

import java.util.Random;

import observerpattern.Beobachtbarer;

public class Wuerfel extends Beobachtbarer
{
	int zahl = 1;

	public Wuerfel(int zahl)
	{
		this.zahl = zahl;
	}

	@Deprecated
	public void wuerfeln()
	{
		Random r = new Random();
		setZahl(1 + r.nextInt(6));
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
