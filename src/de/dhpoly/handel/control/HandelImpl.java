package de.dhpoly.handel.control;

import de.dhpoly.feld.Feld;
import de.dhpoly.handel.Handel;
import de.dhpoly.handel.model.Transaktion;

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
		for (Feld feld : transaktion.getFelderBekommen())
		{
			feld.setEigentuemer(transaktion.getAnbietender());
		}

		// Felder abgeben
		for (Feld feld : transaktion.getFelderGeben())
		{
			feld.setEigentuemer(transaktion.getHandelspartner());
		}

		// Geld transferieren
		transaktion.getAnbietender().ueberweiseGeld(transaktion.getGeldbetrag(), transaktion.getHandelspartner());
	}
}
