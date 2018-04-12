package de.dhpoly.fehler.control;

import org.junit.Ignore;
import org.junit.Test;

import de.dhpoly.fehler.model.Fehler;
import de.dhpoly.fehler.model.FehlerTyp;

public class FehlerImplTest
{
	@Ignore
	@Test
	public void fehlerNachricht() // NOSONAR
	{
		Fehler fehler = new Fehler("Testfehler aus JUnit. Der Fehler kann ignoriert werden.", FehlerTyp.FEHLER_ALLE);
		new FehlerImpl(null).fehlerAufgetreten(fehler);
	}
}
