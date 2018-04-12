package de.dhpoly.feld.view;

import java.util.ArrayList;

import de.dhpoly.feld.control.Ereignisfeld;
import de.dhpoly.kartenstapel.control.KartenstapelImpl;
import de.dhpoly.utils.Spielansicht;

public class EreignisfeldUITest // NOSONAR
{
	public static void main(String[] args)
	{
		Ereignisfeld feld = new Ereignisfeld(new KartenstapelImpl(new ArrayList<>()));
		Spielansicht.zeige(new EreignisfeldUI(feld, Spielansicht.getSpielfeldAnsicht()));
	}
}
