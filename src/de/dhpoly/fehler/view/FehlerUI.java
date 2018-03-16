package de.dhpoly.fehler.view;

import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.Oberflaeche;

public class FehlerUI
{
	public FehlerUI(String fehler)
	{
		Oberflaeche.getInstance().zeigeKomplettesFenster(ElementFactory.getNachrichtPanel("Fehler", fehler));
	}
}
