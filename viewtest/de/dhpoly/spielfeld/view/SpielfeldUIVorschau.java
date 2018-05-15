package de.dhpoly.spielfeld.view;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.feld.model.FeldDaten;
import de.dhpoly.feld.model.LosfeldDaten;
import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.spiel.model.SpielfeldDaten;
import de.dhpoly.utils.Spielansicht;

public class SpielfeldUIVorschau
{
	public static void main(String[] args)
	{
		List<FeldDaten> felder = new ArrayList<>();

		felder.add(new LosfeldDaten());

		for (int i = 0; i < 39; i++)
		{
			felder.add(new StrasseDaten());
		}

		Spielansicht.zeige(new SpielfeldUI(new SpielfeldDaten(felder), Spielansicht.getSpielfeldAnsicht()));
	}
}
