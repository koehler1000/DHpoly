package de.dhpoly.nachricht.control;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.empfaenger.model.Empfaenger;
import de.dhpoly.fehler.control.EntwicklerInformierenLogikImpl;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielImpl;

public class EntwicklerInformierenLogikImplTest
{
	@Test
	@Ignore
	public void test() // NOSONAR
	{
		try
		{
			new EntwicklerInformierenLogikImpl().sendTelegramMessage("Testfehler",
					"Das ist ein Fehler aus dem JUnitTest. Dieser ist beabsichtigt und darf ignoriert werden.");
		}
		catch (IOException ex)
		{
			System.err.println("Fehler beim Senden der Nachricht - " + ex.getClass() + " - " + ex.getMessage());
			fail("Senden fehlgeschlagen - " + ex.getMessage());
		}
	}

	@Test
	@Ignore
	public void spielEmpfangen() throws Exception // NOSONAR
	{
		Datenobjekt fehler = new Nachricht("Fehler aus Junit. Neuer fehler", Empfaenger.ENTWICKLER);
		Spiel spiel = new SpielImpl();
		spiel.empfange(fehler);
	}

}
