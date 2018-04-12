package de.dhpoly.fehler.control;

import java.io.IOException;
import java.util.Optional;

import de.dhpoly.fehler.model.Fehler;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class FehlerVerarbeiterImpl implements de.dhpoly.fehler.FehlerVerarbeiter
{
	Optional<SpielfeldAnsicht> ansicht;

	public FehlerVerarbeiterImpl(SpielfeldAnsicht ansicht)
	{
		this.ansicht = Optional.ofNullable(ansicht);
	}

	public void fehlerAufgetreten(Fehler fehler)
	{
		if (fehler.getFehlertyp().isEntwicklerInformieren())
		{
			stillerFehler(fehler.getFehlertext());
		}

		ansicht.ifPresent(e -> e.zeigeObjekt(fehler));
	}

	public static void stillerFehler(String nachricht)
	{
		try
		{
			TelegramNotification.sendTelegramMessage("Fehler", nachricht);
		}
		catch (IOException ex)
		{
			// ignore
		}
	}
}
