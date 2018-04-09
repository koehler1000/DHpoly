package de.dhpoly.fehler;

import de.dhpoly.fehler.control.FehlerImpl;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public interface Fehler
{
	static void fehlerAufgetreten(String nachricht, SpielfeldAnsicht ansicht)
	{
		new FehlerImpl(ansicht).fehlerAufgetreten(nachricht);
	}

	static void fehlerAufgetreten(Exception ex, SpielfeldAnsicht ansicht)
	{
		new FehlerImpl(ansicht).fehlerAufgetreten(ex);
	}
}
