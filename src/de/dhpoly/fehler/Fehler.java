package de.dhpoly.fehler;

import de.dhpoly.fehler.control.FehlerImpl;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public interface Fehler
{
	static void fehlerAufgetreten(de.dhpoly.fehler.model.Fehler fehler, SpielfeldAnsicht ansicht)
	{
		new FehlerImpl(ansicht).fehlerAufgetreten(fehler);
	}
}
