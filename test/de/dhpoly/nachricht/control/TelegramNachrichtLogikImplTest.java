package de.dhpoly.nachricht.control;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

public class TelegramNachrichtLogikImplTest
{
	@Test
	@Ignore
	public void test() // NOSONAR
	{
		try
		{
			new TelegramNachrichtLogikImpl().sendTelegramMessage("Testfehler",
					"Das ist ein Fehler aus dem JUnitTest. Dieser ist beabsichtigt und darf ignoriert werden.");
		}
		catch (IOException ex)
		{
			System.err.println("Fehler beim Senden der Nachricht - " + ex.getClass() + " - " + ex.getMessage());
			fail("Senden fehlgeschlagen - " + ex.getMessage());
		}
	}
}
