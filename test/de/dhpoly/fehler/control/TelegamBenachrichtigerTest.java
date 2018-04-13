package de.dhpoly.fehler.control;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class TelegamBenachrichtigerTest
{
	@Ignore
	@Test
	public void test() // NOSONAR
	{
		try
		{
			TelegamBenachrichtiger.sendTelegramMessage("Testfehler",
					"Das ist ein Fehler aus dem JUnitTest. Dieser ist beabsichtigt und darf ignoriert werden.");
		}
		catch (IOException ex)
		{
			System.err.println("Fehler beim Senden der Nachricht - " + ex.getClass() + " - " + ex.getMessage());
			fail("Senden fehlgeschlagen - " + ex.getMessage());
		}
	}

}
