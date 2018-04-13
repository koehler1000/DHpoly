package de.dhpoly.wuerfel.control;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.wuerfel.Wuerfelpaar;
import observerpattern.Beobachtbarer;

public class WuerfelpaarImpl extends Beobachtbarer implements Wuerfelpaar
{
	private List<Wuerfel> wuerfel = new ArrayList<>();

	public WuerfelpaarImpl()
	{
		wuerfel.add(new Wuerfel(1));
		wuerfel.add(new Wuerfel(4));
	}

	@Override
	public void wuerfeln()
	{
		wuerfel.forEach(Wuerfel::wuerfeln);
		informiereBeobachter();
	}

	@Override
	public int getWuerfelErgebnis1()
	{
		return wuerfel.get(0).getZahl();
	}

	@Override
	public int getWuerfelErgebnis2()
	{
		return wuerfel.get(1).getZahl();
	}

	@Override
	public int berechneWuerfelSumme()
	{
		return getWuerfelErgebnis1() + getWuerfelErgebnis2();
	}

	@Override
	public List<Wuerfel> getWuerfel()
	{
		return wuerfel;
	}
}
