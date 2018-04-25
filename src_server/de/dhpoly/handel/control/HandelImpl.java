package de.dhpoly.handel.control;

import java.io.IOException;
import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.FeldStrasse;
import de.dhpoly.handel.Handel;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.handel.model.TransaktionsTyp;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.Spieler;

public class HandelImpl implements Handel
{
	@Override
	public void vorschlagAnbieten(Transaktion transaktion)
	{
		transaktion.getAnbietender().zeigeDatenobjekt(transaktion);
	}

	@Override
	public void vorschlagAnnehmen(Transaktion transaktion)
	{
		transaktion.setTransaktionsTyp(TransaktionsTyp.ANGENOMMEN);

		Spieler anbietender = transaktion.getAnbietender();
		Spieler handelspartner = transaktion.getHandelspartner();

		// Felder Eigentum �bertragen
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

		anbietender.zeigeDatenobjekt(transaktion);
		handelspartner.zeigeDatenobjekt(transaktion);
	}

	private void eigentumUebertragen(List<Feld> felderEigentumswechsel, Spieler anbietender, Spieler handelspartner)
	{
		for (Feld feld : felderEigentumswechsel)
		{
			eigentumUebertragen(feld, anbietender, handelspartner);
		}
	}

	private void eigentumUebertragen(Feld feld, Spieler anbietender, Spieler handelspartner)
	{
		if (feld instanceof FeldStrasse)
		{
			FeldStrasse strasse = (FeldStrasse) feld;

			if (strasse.gehoertSpieler(anbietender))
			{
				strasse.setEigentuemer(handelspartner);
			}
			else
			{
				strasse.setEigentuemer(anbietender);
			}
		}
	}

	@Override
	public void vorschlagAblehnen(Transaktion transaktion)
	{
		transaktion.setTransaktionsTyp(TransaktionsTyp.ABGELEHNT);
	}

	@Override
	public void verarbeite(Datenobjekt objekt) throws IOException
	{
		Transaktion transaktion = (Transaktion) objekt;
		switch (transaktion.getTransaktionsTyp())
		{
			case ABGELEHNT:
				vorschlagAblehnen(transaktion);
				break;
			case ANGENOMMEN:
				vorschlagAnnehmen(transaktion);
				break;
			case NEU:
				vorschlagAnbieten(transaktion);
				break;
			case NEUER_VORSCHLAG:
				vorschlagAnbieten(transaktion);
				break;
			case VORSCHLAG:
				vorschlagAnbieten(transaktion);
				break;
			default:
				break;
		}
	}
}
