package de.dhpoly.spieler.control;

import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.karte.Karte;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.Spieler;
import observerpattern.Beobachter;

public class SpielerUnimplemented implements Spieler
{

	@Override
	public void einzahlen(RessourcenDatensatz datensatz)
	{}

	@Override
	public void einzahlen(List<RessourcenDatensatz> datensaetze)
	{}

	@Override
	public void auszahlen(RessourcenDatensatz datensatz)
	{}

	@Override
	public void auszahlen(List<RessourcenDatensatz> datensaetze)
	{}

	@Override
	public void ueberweise(RessourcenDatensatz datensatz, Spieler empfaenger)
	{}

	@Override
	public boolean isNegative()
	{
		return false;
	}

	@Override
	public int getFeldNr()
	{
		return 0;
	}

	@Override
	public String getName()
	{
		return null;
	}

	@Override
	public void setFeldNr(int feldNrSoll)
	{}

	@Override
	public void zeigeTransaktionsvorschlag(Transaktion transaktion)
	{}

	@Override
	public void zeigeKaufmoeglichkeit(Strasse strasse)
	{}

	@Override
	public void zeigeKarte(Karte karte)
	{}

	@Override
	public int getSpielerNr()
	{
		return 0;
	}

	@Override
	public void setAkutellerSpieler(boolean isAktuell)
	{}

	@Override
	public boolean isAktuellerSpieler()
	{
		return false;
	}

	@Override
	public void addBeobachterHinzu(Beobachter beobachter)
	{}

	@Override
	public List<RessourcenDatensatz> getRessourcenTransaktionen()
	{
		return null;
	}

	@Override
	public int getRessourcenWerte(Ressource ressource)
	{
		return 0;
	}

	@Override
	public boolean kannBezahlen(List<RessourcenDatensatz> kosten)
	{
		return false;
	}

	@Override
	public void ausscheiden()
	{}

	@Override
	public void gewonnen()
	{}

	@Override
	public List<Feld> getFelder()
	{
		return null;
	}

	@Override
	public void feldHinzu(Feld feld)
	{}

	@Override
	public void feldWeg(Feld feld)
	{}

	@Override
	public void setSpielerNr(int nr)
	{}

	@Override
	public boolean hatVerloren()
	{
		return false;
	}

	@Override
	public void setSpielfeldAnsicht(SpielfeldAnsicht ansicht)
	{}

	@Override
	public void zeigeTransaktionErfolgreich(Transaktion transaktion)
	{}

	@Override
	public void zeigeTransaktionGescheitert(Transaktion transaktion)
	{}

	@Override
	public void sperreOberflaeche(Transaktion transaktion)
	{}

	@Override
	public void zeigeHausbauMoeglichkeit()
	{}

	@Override
	public void zeigeDatenobjekt(Datenobjekt objekt)
	{}

	@Override
	public void leereRand()
	{}
}
