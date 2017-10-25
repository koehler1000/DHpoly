package de.dhpoly.fehler;

import de.dhpoly.fehler.control.FehlerImpl;

public interface Fehler
{
	static void fehlerAufgetreten(String nachricht)
	{
		FehlerImpl.fehlerAufgetreten(nachricht);
	}

	static void fehlerAufgetreten(Exception ex)
	{
		FehlerImpl.fehlerAufgetreten(ex);
	}
}
