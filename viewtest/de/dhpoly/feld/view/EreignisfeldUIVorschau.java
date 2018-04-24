package de.dhpoly.feld.view;

import java.util.ArrayList;

import de.dhpoly.feld.control.FeldEreignis;
import de.dhpoly.kartenstapel.control.KartenstapelImpl;
import de.dhpoly.utils.Spielansicht;

public class EreignisfeldUIVorschau
{
	public static void main(String[] args)
	{
		FeldEreignis feld = new FeldEreignis(new KartenstapelImpl(new ArrayList<>()));
		Spielansicht.zeige(new EreignisfeldUI(feld, Spielansicht.getSpielfeldAnsicht()));
	}
}
