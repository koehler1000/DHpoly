package de.dhpoly.wuerfel.view;

import de.dhpoly.utils.Spielansicht;
import de.dhpoly.wuerfel.control.WuerfelpaarImpl;

public class WuerfelUIVorschau
{
	public static void main(String[] args)
	{
		WuerfelpaarImpl wuerfel = new WuerfelpaarImpl();
		Spielansicht.zeige(new WuerfelUI(wuerfel.getWuerfel().get(0), Spielansicht.getSpielfeldAnsicht()));
	}
}
