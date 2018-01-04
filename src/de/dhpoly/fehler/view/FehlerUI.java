package de.dhpoly.fehler.view;

import de.dhpoly.fenster.view.Fenster;

public class FehlerUI
{
	public FehlerUI(String fehler)
	{
		Fenster.zeigeInfo("Fehler", fehler);
	}
}
