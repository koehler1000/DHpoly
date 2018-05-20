package de.dhpoly.nachricht.control;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.nachricht.SpielerInformierenLogik;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.spiel.Spiel;

public class SpielerInformierenLogikImpl implements SpielerInformierenLogik
{
	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel)
	{
		if (objekt instanceof Nachricht)
		{
			sendeNachricht((Nachricht) objekt, spiel);
		}
	}

	@Override
	public void sendeNachricht(Nachricht nachricht, Spiel spiel)
	{
		if (nachricht.getEmpfaenger().isAlleSpielerInformieren())
		{
			spiel.zeigeAllenSpielern(nachricht);
		}
		else if (nachricht.getEmpfaenger().isAktuellenSpielerInformieren())
		{
			spiel.zeigeSpieler(spiel.getAktuellerSpieler(), nachricht);
		}
	}
}
