package de.dhpoly.fehler.control;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class TelegramNotificationTest
{

	@Test
	public void test()
	{
		try
		{
			TelegramNotification.sendTelegramMessage("Testfehler",
					"Das ist ein Fehler aus dem JUnitTest. Dieser ist beabsichtigt und darf ignoriert werden.");
		}
		catch (IOException ex)
		{
			System.err.println("Fehler beim Senden der Nachricht - " + ex.getClass() + " - " + ex.getMessage());
			fail("Senden fehlgeschlagen - " + ex.getMessage());
		}
	}

}
