package de.dhpoly.wuerfel.control;

import java.util.Random;

public class Wuerfel
{
	int zahl = 1;

	public Wuerfel(int zahl)
	{
		this.zahl = zahl;
	}

	public void wuerfeln()
	{
		Random r = new Random();
		zahl = 1 + r.nextInt(6);
	}

	public int getZahl()
	{
		return zahl;
	}
}
