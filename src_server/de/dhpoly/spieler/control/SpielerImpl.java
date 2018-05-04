package de.dhpoly.spieler.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.FeldRessource;
import de.dhpoly.feld.control.FeldStrasse;
import de.dhpoly.feld.model.StrasseKaufen;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.model.SpielerDaten;

public abstract class SpielerImpl implements Spieler
{
	int feldNr = 0;
	Spiel spiel;
	boolean aktuellerSpieler = false;
	boolean verloren = false;

	SpielerDaten daten;

	public SpielerImpl(SpielerDaten daten, Spiel spiel)
	{
		this.daten = daten;
		this.spiel = spiel;
	}

	@Override
	public int getSpielerNr()
	{
		return daten.getSpielerNr();
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

	@Override
	public void setAktuellerSpieler(boolean isAktuell)
	{
		aktuellerSpieler = isAktuell;
	}

	@Override
	public boolean isAktuellerSpieler()
	{
		return aktuellerSpieler;
	}

	@Override
	public void einzahlen(RessourcenDatensatz datensatz)
	{
		daten.einzahlen(datensatz);
	}

	@Override
	public void auszahlen(RessourcenDatensatz datensatz)
	{
		daten.auszahlen(datensatz);
	}

	@Override
	public List<RessourcenDatensatz> getRessourcenKontoauszug()
	{
		return daten.getRessourcenKontoauszug();
	}

	public int getRessourcenWerte(Ressource ressource)
	{
		return daten.getRessourcenWert(ressource);
	}

	@Override
	public void einzahlen(List<RessourcenDatensatz> datensaetze)
	{
		daten.einzahlen(datensaetze);
	}

	@Override
	public void auszahlen(List<RessourcenDatensatz> datensaetze)
	{
		daten.auszahlen(datensaetze);
	}

	@Override
	public boolean kannBezahlen(List<RessourcenDatensatz> kostenHaus)
	{
		for (RessourcenDatensatz ressourcenDatensatz : kostenHaus)
		{
			if (getRessourcenWerte(ressourcenDatensatz.getRessource()) < ressourcenDatensatz.getAnzahl())
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public void ausscheiden()
	{
		strassenZurueckgeben();
		verloren = true;
	}

	protected void strassenZurueckgeben()
	{
		List<Feld> felder = spiel.getFelder(this);
		while (!felder.isEmpty())
		{
			Feld feld = felder.get(0);
			if (feld instanceof FeldStrasse)
			{
				FeldStrasse strasse = (FeldStrasse) feld;
				strasse.zurueckgeben();
			}
		}
	}

	@Override
	public void gewonnen()
	{
		zeigeDatenobjekt(new Nachricht("Gewonnen"));
	}

	@Override
	public List<Feld> getFelder()
	{
		if (Optional.ofNullable(spiel).isPresent())
		{
			return spiel.getFelder(this);
		}
		else
		{
			return new ArrayList<>();
		}
	}

	@Override
	public void setSpielerNr(int nr)
	{
		daten.setSpielerNr(nr);
	}

	public boolean hatVerloren()
	{
		return verloren;
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

				einzahlen(res);
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
