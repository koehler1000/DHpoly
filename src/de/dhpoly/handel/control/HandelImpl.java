package de.dhpoly.handel.control;

import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.handel.Handel;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.Spieler;

public class HandelImpl implements Handel
{
	@Override
	public void vorschlagAnbieten(Transaktion transaktion)
	{
		transaktion.getHandelspartner().zeigeTransaktionsvorschlag(transaktion);
	}

	@Override
	public void vorschlagAnnehmen(Transaktion transaktion)
	{
		Spieler anbietender = transaktion.getAnbietender();
		Spieler handelspartner = transaktion.getHandelspartner();

		// Felder Eigentum übertragen
		eigentumUebertragen(transaktion.getFelderEigentumswechsel(), anbietender, handelspartner);

		for (Ressource res : Ressource.values())
		{
			anbietender.ueberweise(new RessourcenDatensatz(res, transaktion.getRessource(anbietender, res)),
					handelspartner);

			handelspartner.ueberweise(new RessourcenDatensatz(res, transaktion.getRessource(handelspartner, res)),
					anbietender);
		}

		// Ressourcen transferieren
		transaktion.getAnbietender().zeigeTransaktionErfolgreich(transaktion);
		transaktion.getHandelspartner().zeigeTransaktionErfolgreich(transaktion);
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
		if (feld instanceof Strasse)
		{
			Strasse strasse = (Strasse) feld;

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
}
