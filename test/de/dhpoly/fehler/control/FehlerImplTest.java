package de.dhpoly.fehler.control;

import org.junit.Ignore;
import org.junit.Test;

public class FehlerImplTest
{
	@Ignore
	@Test
	public void fehlerNachricht() // NOSONAR
	{
		new FehlerImpl(null).fehlerAufgetreten("Testfehler aus JUnit. Der Fehler kann ignoriert werden.");
	}

	@Ignore
	@Test
	public void fehlerException() // NOSONAR
	{
		new FehlerImpl(null)
				.fehlerAufgetreten(new Exception("Testfehler aus JUnit. Der Fehler kann ignoriert werden."));
	}

}
