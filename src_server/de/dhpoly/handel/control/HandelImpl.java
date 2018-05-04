package de.dhpoly.handel.control;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.FeldStrasse;
import de.dhpoly.handel.Handel;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.handel.model.TransaktionsTyp;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.model.SpielerDaten;

public class HandelImpl implements Handel
{
	@Override
	public void vorschlagAnbieten(Transaktion transaktion, Spiel spiel)
	{
		Optional<Spieler> anbietender = spiel.getSpieler(transaktion.getAnbietender());
		anbietender.ifPresent(sp -> sp.zeigeDatenobjekt(transaktion));
	}

	@Override
	public void vorschlagAnnehmen(Transaktion transaktion, Spiel spiel)
	{
		transaktion.setTransaktionsTyp(TransaktionsTyp.ANGENOMMEN);

		spiel.getSpieler(transaktion.getAnbietender()).ifPresent(
				anbietender -> spiel.getSpieler(transaktion.getHandelspartner()).ifPresent(handelspartner -> {

					// Felder Eigentum übertragen
					eigentumUebertragen(transaktion.getFelderEigentumswechsel(), anbietender.getDaten(),
							handelspartner.getDaten());

					for (Ressource res : Ressource.values())
					{
						RessourcenDatensatz geben = new RessourcenDatensatz(res,
								transaktion.getRessource(anbietender.getDaten(), res));
						anbietender.auszahlen(geben);
						handelspartner.einzahlen(geben);

						RessourcenDatensatz bekommen = new RessourcenDatensatz(res,
								transaktion.getRessource(handelspartner.getDaten(), res));
						anbietender.einzahlen(bekommen);
						handelspartner.auszahlen(bekommen);
					}

					anbietender.zeigeDatenobjekt(transaktion);
					handelspartner.zeigeDatenobjekt(transaktion);
				}));
	}

	private void eigentumUebertragen(List<Feld> felderEigentumswechsel, SpielerDaten spielerDaten,
			SpielerDaten spielerDaten2)
	{
		for (Feld feld : felderEigentumswechsel)
		{
			eigentumUebertragen(feld, spielerDaten, spielerDaten2);
		}
	}

	private void eigentumUebertragen(Feld feld, SpielerDaten anbietender, SpielerDaten handelspartner)
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
	public void vorschlagAblehnen(Transaktion transaktion, Spiel spiel)
	{
		transaktion.setTransaktionsTyp(TransaktionsTyp.ABGELEHNT);
	}

	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel) throws IOException
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
