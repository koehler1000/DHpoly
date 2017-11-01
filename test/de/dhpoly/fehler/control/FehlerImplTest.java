package de.dhpoly.fehler.control;

import org.junit.Ignore;
import org.junit.Test;

public class FehlerImplTest
{
	@Ignore
	@Test
	public void fehlerNachricht()
	{
		FehlerImpl.fehlerAufgetreten("Testfehler aus JUnit. Der Fehler kann ignoriert werden.");
	}

	@Ignore
	@Test
	public void fehlerException()
	{
		FehlerImpl.fehlerAufgetreten(new Exception("Testfehler aus JUnit. Der Fehler kann ignoriert werden."));
	}

}
