package de.dhpoly.handel.control;

import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.handel.Handel;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.ressource.control.RessourcenDatensatzImpl;
import de.dhpoly.ressource.model.Ressource;
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
		EigentumUebertragen(transaktion.getFelderGeben(), transaktion.getHandelspartner());

		// Felder abgeben
		EigentumUebertragen(transaktion.getFelderBekommen(), transaktion.getAnbietender());

		// Geld transferieren
		transaktion.getAnbietender().ueberweise(
				new RessourcenDatensatzImpl(Ressource.GELD, transaktion.getGeldbetrag(),
						"Handel zwischen " + transaktion.getAnbietender() + " und " + transaktion.getHandelspartner()),
				transaktion.getHandelspartner());
	}

	private void EigentumUebertragen(List<Feld> felder, Spieler neuerEigentuemer)
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
