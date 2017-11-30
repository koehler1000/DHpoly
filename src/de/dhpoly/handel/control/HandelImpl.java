package de.dhpoly.handel.control;

import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.handel.Handel;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.spieler.Spieler;

public class HandelImpl implements Handel
{

	@Override
	public void vorschlagAnbieten(Transaktion transaktion)
	{
		transaktion.getHandelspartner().zeigeTransaktionsvorschlag(transaktion);
	}

	@Override
	public void vorschlagAnnehmen(Transaktion transaktion)
	{
		// Felder erhalten
		eigentumUebertragen(transaktion.getFelderGeben(), transaktion.getHandelspartner());

		// Felder abgeben
		eigentumUebertragen(transaktion.getFelderBekommen(), transaktion.getAnbietender());

		// Geld transferieren
		transaktion.getAnbietender().einzahlen(transaktion.getRessourcenBekommen());
		transaktion.getAnbietender().auszahlen(transaktion.getRessourcenGeben());

		transaktion.getHandelspartner().einzahlen(transaktion.getRessourcenGeben());
		transaktion.getHandelspartner().auszahlen(transaktion.getRessourcenBekommen());
	}

	private void eigentumUebertragen(List<Feld> felder, Spieler neuerEigentuemer)
	{
		for (Feld feld : felder)
		{
			if (feld instanceof Strasse)
			{
				Strasse strasse = (Strasse) feld;
				strasse.setEigentuemer(neuerEigentuemer);
			}
		}
	}
}
