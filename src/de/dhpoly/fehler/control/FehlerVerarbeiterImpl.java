package de.dhpoly.fehler.control;

import java.io.IOException;
import java.util.Optional;

import de.dhpoly.fehler.FehlerVerarbeiter;
import de.dhpoly.fehler.model.Fehler;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class FehlerVerarbeiterImpl implements FehlerVerarbeiter
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
		if (fehler.getFehlertyp().isAktuellenSpielerInformieren())
		{
			ansicht.ifPresent(e -> e.zeigeObjekt(fehler));
		}
		if (fehler.getFehlertyp().isAlleSpielerInformieren())
		{
			// TODO andere Spieler informieren
		}
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
