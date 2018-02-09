package de.dhpoly.fehler.control;

import java.io.IOException;

import de.dhpoly.fehler.Fehler;
import de.dhpoly.fehler.view.FehlerUI;

public class FehlerImpl implements Fehler
{
	private FehlerImpl()
	{
		// keine Instanzen
	}

	public static void fehlerAufgetreten(String nachricht)
	{
		stillerFehler(nachricht);
		new FehlerUI(nachricht);
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
