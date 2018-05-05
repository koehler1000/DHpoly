package de.dhpoly.spieler.control;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.FeldRessource;
import de.dhpoly.feld.model.StrasseKaufen;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.model.SpielerDaten;
import de.dhpoly.spieler.model.SpielerStatus;

public abstract class SpielerImpl implements Spieler
{
	int feldNr = 0;
	Spiel spiel;

	SpielerDaten daten;

	public SpielerImpl(SpielerDaten daten, Spiel spiel)
	{
		this.daten = daten;
		this.spiel = spiel;
	}

	public int getFeldNr()
	{
		return feldNr;
	}

	public String getName()
	{
		return daten.getName();
	}

	public void setFeldNr(int feldNrSoll)
	{
		this.feldNr = feldNrSoll;
	}

	public boolean isNegative()
	{
		return getRessourcenWerte(Ressource.GELD) < 0;
	}

	public int getRessourcenWerte(Ressource ressource)
	{
		return daten.getRessourcenWert(ressource);
	}

	public boolean hatVerloren()
	{
		return daten.getStatus() == SpielerStatus.VERLOREN;
	}

	@Override
	public void vergebeRessourcen(int ertrag)
	{
		for (Feld feld : spiel.getFelder(this))
		{
			if (feld instanceof FeldRessource)
			{
				FeldRessource ressourcenfeld = (FeldRessource) feld;
				RessourcenDatensatz res = new RessourcenDatensatz(ressourcenfeld.getRessource(), ertrag);

				getDaten().einzahlen(res);
			}
		}
	}

	@Override
	public void kaufe(StrasseKaufen strasse)
	{
		spiel.kaufe(strasse, this);
	}

	@Override
	public SpielerDaten getDaten()
	{
		return daten;
	}

	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel)
	{}
}
