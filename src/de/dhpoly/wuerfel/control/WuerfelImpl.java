package de.dhpoly.wuerfel.control;

import java.util.Random;

import de.dhpoly.wuerfel.Wuerfel;
import observerpattern.Beobachtbarer;

public class WuerfelImpl extends Beobachtbarer implements Wuerfel
{
	private int wuerfelErgebnis1 = 1;
	private int wuerfelErgebnis2 = 4;

	@Override
	public void wuerfeln()
	{
		wuerfelErgebnis1 = getWuerfelZufall();
		wuerfelErgebnis2 = getWuerfelZufall();

		informiereBeobachter();
	}

	@Override
	public int getWuerfelZufall()
	{
		Random r = new Random();
		return 1 + r.nextInt(6);
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
