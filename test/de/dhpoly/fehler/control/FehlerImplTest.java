package de.dhpoly.fehler.control;

import org.junit.Test;

public class FehlerImplTest
{

	@Test
	public void fehlerNachricht()
	{
		FehlerImpl.fehlerAufgetreten("Testfehler aus JUnit. Der Fehler kann ignoriert werden.");
	}

	@Test
	public void fehlerException()
	{
		FehlerImpl.fehlerAufgetreten(new Exception("Testfehler aus JUnit. Der Fehler kann ignoriert werden."));
	}

}
