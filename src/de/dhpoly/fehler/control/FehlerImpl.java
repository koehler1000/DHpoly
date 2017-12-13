package de.dhpoly.fehler.control;

import java.io.IOException;

import javax.swing.JOptionPane;

import de.dhpoly.fehler.Fehler;

public class FehlerImpl implements Fehler
{
	public static void fehlerAufgetreten(String nachricht)
	{
		stillerFehler(nachricht);
		JOptionPane.showMessageDialog(null, nachricht, "Fehler", JOptionPane.ERROR_MESSAGE);
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
