package de.dhpoly.handel.control;

import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.empfaenger.model.Empfaenger;
import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.logik.Logik;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.model.Spieler;

public class HandelLogik implements Logik
{
	public void vorschlagAnnehmen(Transaktion transaktion, Spiel spiel)
	{
		Spieler anbietender = transaktion.getAnbietender();
		Spieler handelspartner = transaktion.getHandelspartner();

		// Felder Eigentum übertragen
		eigentumUebertragen(transaktion.getFelderEigentumswechsel(), anbietender, handelspartner);

		for (Ressource res : Ressource.values())
		{
			RessourcenDatensatz geben = new RessourcenDatensatz(res, transaktion.getRessource(anbietender, res),
					"Handel");
			anbietender.auszahlen(geben);
			handelspartner.einzahlen(geben);

			RessourcenDatensatz bekommen = new RessourcenDatensatz(res, transaktion.getRessource(handelspartner, res),
					"Handel");
			anbietender.einzahlen(bekommen);
			handelspartner.auszahlen(bekommen);
		}
	}

	private void eigentumUebertragen(List<StrasseDaten> feld2, Spieler spielerDaten, Spieler spielerDaten2)
	{
		for (StrasseDaten feld : feld2)
		{
			eigentumUebertragen(feld, spielerDaten, spielerDaten2);
		}
	}

	private void eigentumUebertragen(StrasseDaten feld, Spieler anbietender, Spieler handelspartner)
	{
		if (feld.gehoertSpieler(anbietender))
		{
			feld.setEigentuemer(handelspartner);
		}
		else
		{
			feld.setEigentuemer(anbietender);
		}
	}

	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel)
	{
		if (objekt instanceof Transaktion)
		{
			Transaktion transaktion = (Transaktion) objekt;
			transaktion.nichtEinverstandeneSpieler().forEach(s -> spiel.zeigeSpieler(s, transaktion));

			if (transaktion.nichtEinverstandeneSpieler().isEmpty())
			{
				vorschlagAnnehmen(transaktion, spiel);
				Nachricht nachricht = new Nachricht("Handel angenommen", Empfaenger.ALLE_SPIELER);
				spiel.empfange(nachricht);
			}
		}
	}
}
