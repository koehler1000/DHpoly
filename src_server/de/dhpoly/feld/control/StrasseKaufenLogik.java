package de.dhpoly.feld.control;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.empfaenger.model.Empfaenger;
import de.dhpoly.fehler.model.Fehler;
import de.dhpoly.feld.model.StrasseKaufen;
import de.dhpoly.feld.model.StrasseKaufenStatus;
import de.dhpoly.logik.Logik;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.model.Spieler;

public class StrasseKaufenLogik implements Logik
{
	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel)
	{
		if (objekt instanceof StrasseKaufen)
		{
			StrasseKaufen strasseKaufen = (StrasseKaufen) objekt;

			if (spiel.kannSpielerStrasseKaufen(strasseKaufen.getSender(), strasseKaufen.getStrasse()))
			{
				kaufAbwickeln(strasseKaufen, strasseKaufen.getSender());
				spiel.zeigeAllenSpielern(spiel.getSpielfeld());
			}
			else
			{
				spiel.zeigeSpieler(strasseKaufen.getSender(),
						new Fehler("Kauf fehlgeschlagen: Das Kaufangebot ist veraltet", Empfaenger.AKTUELLER_SPIELER));
			}
		}
	}

	private void kaufAbwickeln(StrasseKaufen strasse, Spieler sp)
	{
		if (strasse.isKaufbar() && sp.kannBezahlen(strasse.getKaufpreis()))
		{
			sp.auszahlen(strasse.getKaufpreis());
			strasse.setEigentuemer(sp);
			strasse.setStatus(StrasseKaufenStatus.ANGENOMMEN);
			sp.addStrasse(strasse.getStrasse());
		}
	}
}
