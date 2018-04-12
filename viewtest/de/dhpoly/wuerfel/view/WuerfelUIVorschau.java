package de.dhpoly.wuerfel.view;

import de.dhpoly.utils.Spielansicht;
import de.dhpoly.wuerfel.control.WuerfelImpl;

public class WuerfelUIVorschau
{
	public static void main(String[] args)
	{
		WuerfelImpl wuerfel = new WuerfelImpl();
		Spielansicht.zeige(new WuerfelUI(wuerfel, 1, Spielansicht.getSpielfeldAnsicht()));
	}
}
