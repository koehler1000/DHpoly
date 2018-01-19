package de.dhpoly.wuerfel.control;

import java.util.Random;

import de.dhpoly.wuerfel.Wuerfel;
import observerpattern.Beobachtbarer;

public class WuerfelImpl extends Beobachtbarer implements Wuerfel
{
	private int wuerfelErgebnis1 = 1;
	private int wuerfelErgebnis2 = 1;

	@Override
	public void wuerfeln()
	{
		wuerfelErgebnis1 = getWuerfelZufall();
		wuerfelErgebnis2 = getWuerfelZufall();

		informiereBeobachter();
	}

	public int getWuerfelZufall()
	{
		Random r = new Random();
		int erg = 1 + r.nextInt(6);
		return erg;
	}

	@Override
	public int getWuerfelErgebnis1()
	{
		return wuerfelErgebnis1;
	}

	@Override
	public int getWuerfelErgebnis2()
	{
		return wuerfelErgebnis2;
	}

	@Override
	public int getWuerfelErgebnisSumme()
	{
		return wuerfelErgebnis1 + wuerfelErgebnis2;
	}
}
