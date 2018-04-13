package de.dhpoly.fehler;

import de.dhpoly.fehler.control.FehlerVerarbeiterImpl;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public interface FehlerVerarbeiter
{
	@Deprecated
	static void fehlerAufgetreten(de.dhpoly.fehler.model.Fehler fehler, SpielfeldAnsicht ansicht)
	{
		new FehlerVerarbeiterImpl(ansicht).fehlerAufgetreten(fehler);
	}
}
