package de.dhpoly.fehler.control;

import java.io.IOException;
import java.util.Optional;

import de.dhpoly.fehler.Fehler;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class FehlerImpl implements Fehler
{
	Optional<SpielfeldAnsicht> ansicht;

	public FehlerImpl(SpielfeldAnsicht ansicht)
	{
		this.ansicht = Optional.ofNullable(ansicht);
	}

	public void fehlerAufgetreten(String nachricht)
	{
		stillerFehler(nachricht);
		ansicht.ifPresent(e -> e.zeigeFehler(nachricht));
	}

	public void fehlerAufgetreten(Exception ex)
	{
		fehlerAufgetreten(ex.getMessage());
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
