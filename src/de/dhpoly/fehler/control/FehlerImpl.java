package de.dhpoly.fehler.control;

import java.io.IOException;

import de.dhpoly.fehler.Fehler;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class FehlerImpl implements Fehler
{
	SpielfeldAnsicht ansicht;

	public FehlerImpl(SpielfeldAnsicht ansicht)
	{
		this.ansicht = ansicht;
	}

	public static void fehlerAufgetreten(String nachricht)
	{
		stillerFehler(nachricht);
		// FIXME new FehlerUI(nachricht);
	}

	public static void fehlerAufgetreten(Exception ex)
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
