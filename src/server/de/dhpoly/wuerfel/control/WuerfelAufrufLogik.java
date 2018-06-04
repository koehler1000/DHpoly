package de.dhpoly.wuerfel.control;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.logik.Logik;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.wuerfel.model.WuerfelAufruf;

public class WuerfelAufrufLogik implements Logik
{
	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel)
	{
		if (objekt instanceof WuerfelAufruf)
		{
			WuerfelAufruf aufruf = (WuerfelAufruf) objekt;
			spiel.wuerfeln(aufruf.getSpielerDaten());
		}
	}
}
