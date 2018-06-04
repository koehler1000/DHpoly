package de.dhpoly.handel.control;

import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.handel.Handel;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.handel.model.TransaktionsTyp;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.model.Spieler;

public class HandelImpl implements Handel
{
	@Override
	public void vorschlagAnbieten(Transaktion transaktion, Spiel spiel)
	{
		Spieler anbietender = transaktion.getAnbietender();
		spiel.zeigeSpieler(anbietender, transaktion);
	}

	@Override
	public void vorschlagAnnehmen(Transaktion transaktion, Spiel spiel)
	{
		transaktion.setTransaktionsTyp(TransaktionsTyp.ANGENOMMEN);

		Spieler anbietender = transaktion.getAnbietender();
		Spieler handelspartner = transaktion.getHandelspartner();

		// Felder Eigentum übertragen
		eigentumUebertragen(transaktion.getFelderEigentumswechsel(), anbietender, handelspartner);

		for (Ressource res : Ressource.values())
		{
			RessourcenDatensatz geben = new RessourcenDatensatz(res, transaktion.getRessource(anbietender, res));
			anbietender.auszahlen(geben);
			handelspartner.einzahlen(geben);

			RessourcenDatensatz bekommen = new RessourcenDatensatz(res, transaktion.getRessource(handelspartner, res));
			anbietender.einzahlen(bekommen);
			handelspartner.auszahlen(bekommen);
		}

		spiel.zeigeSpieler(anbietender, transaktion);
		spiel.zeigeSpieler(handelspartner, transaktion);
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
	public void vorschlagAblehnen(Transaktion transaktion, Spiel spiel)
	{
		transaktion.setTransaktionsTyp(TransaktionsTyp.ABGELEHNT);
	}

	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel)
	{
		if (objekt instanceof Transaktion)
		{
			Transaktion transaktion = (Transaktion) objekt;
			switch (transaktion.getTransaktionsTyp())
			{
				case ABGELEHNT:
					vorschlagAblehnen(transaktion, spiel);
					break;
				case ANGENOMMEN:
					vorschlagAnnehmen(transaktion, spiel);
					break;
				case NEU:
					vorschlagAnbieten(transaktion, spiel);
					break;
				case NEUER_VORSCHLAG:
					vorschlagAnbieten(transaktion, spiel);
					break;
				case VORSCHLAG:
					vorschlagAnbieten(transaktion, spiel);
					break;
				default:
					break;
			}
		}
	}
}
