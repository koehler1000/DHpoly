package de.dhpoly.handel.control;

import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.handel.Handel;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.spieler.Spieler;

public class HandelImpl implements Handel
{

	@Override
	public void vorschlagAnbieten(Transaktion transaktion)
	{
		// TODO UI des Spielers verstecken, stattdessen
		// getAnbietender.versteckeTransaktion oder sperreTransaktion
		transaktion.getAnbietender().getUI().ifPresent(e -> e.leereRand());
		transaktion.getHandelspartner().zeigeTransaktionsvorschlag(transaktion);
	}

	@Override
	public void vorschlagAnnehmen(Transaktion transaktion)
	{
		// Felder Eigentum übertragen
		eigentumUebertragen(transaktion.getFelderEigentumswechsel(), transaktion.getAnbietender(),
				transaktion.getHandelspartner());

		// Ressourcen transferieren
		transaktion.getAnbietender().einzahlen(transaktion.getRessourcenBekommen());
		transaktion.getAnbietender().auszahlen(transaktion.getRessourcenGeben());

		transaktion.getHandelspartner().einzahlen(transaktion.getRessourcenGeben());
		transaktion.getHandelspartner().auszahlen(transaktion.getRessourcenBekommen());
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
