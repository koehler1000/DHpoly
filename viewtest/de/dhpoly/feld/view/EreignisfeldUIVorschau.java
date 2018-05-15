package de.dhpoly.feld.view;

import de.dhpoly.feld.model.EreignisfeldDaten;
import de.dhpoly.utils.Spielansicht;

public class EreignisfeldUIVorschau
{
	public static void main(String[] args)
	{
		Spielansicht.zeige(new EreignisfeldUI(new EreignisfeldDaten(), Spielansicht.getSpielfeldAnsicht()));
	}
}
