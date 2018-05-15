package de.dhpoly.feld.control;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.model.Hausbau;
import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.logik.Logik;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spiel.Spiel;

public class Hausbauer implements Logik
{
	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel)
	{
		if (objekt instanceof Hausbau)
		{
			Hausbau hausbau = (Hausbau) objekt;
			fuehreHausbauDurch(hausbau, spiel);
		}
	}

	private void fuehreHausbauDurch(Hausbau objekt, Spiel spiel)
	{
		StrasseDaten strasse = objekt.getStrasse();
		int anzahlZuBauen = objekt.getAnzahl();

		if (anzahlZuBauen > 0)
		{
			if (!strasse.isAlleHaeuserGebaut())
			{
				strasse.getEigentuemer().ifPresent(s -> {
					if (s.kannBezahlen(strasse.getKostenHaus()))
					{
						s.auszahlen(strasse.getKostenHaus());
						strasse.setHaueser(strasse.getHaeuser() + 1);

						Nachricht nachricht = new Nachricht(
								anzahlZuBauen + " Häuser auf " + strasse.getName() + " gebaut.");
						spiel.zeigeAllenSpielern(nachricht);
					}
				});
			}
		}
		else if (anzahlZuBauen < 0)
		{
			if (strasse.haeuserGebaut())
			{
				strasse.setHaueser(strasse.getHaeuser() - 1);
				strasse.getKostenHaus().stream().filter(e -> e.getRessource() != Ressource.GELD)
						.forEach(e -> strasse.getEigentuemer().ifPresent(s -> s.einzahlen(e)));
			}

			Nachricht nachricht = new Nachricht(anzahlZuBauen + " Häuser auf " + strasse.getName() + " abgerissen.");
			spiel.zeigeAllenSpielern(nachricht);
		}
	}
}
