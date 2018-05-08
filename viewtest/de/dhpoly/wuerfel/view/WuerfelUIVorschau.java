package de.dhpoly.wuerfel.view;

import de.dhpoly.utils.Spielansicht;
import de.dhpoly.wuerfel.control.WuerfelpaarImpl;
import de.dhpoly.wuerfel.model.WuerfelDaten;

public class WuerfelUIVorschau
{
	public static void main(String[] args)
	{
		WuerfelpaarImpl wuerfel = new WuerfelpaarImpl();
		Spielansicht.zeige(new WuerfelUI(new WuerfelDaten(wuerfel.getWuerfel()), Spielansicht.getSpielfeldAnsicht()));
	}
}
