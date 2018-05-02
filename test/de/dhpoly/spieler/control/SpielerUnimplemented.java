package de.dhpoly.spieler.control;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.model.StrasseKaufen;
import de.dhpoly.karte.model.Karte;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.model.SpielerDaten;
import de.dhpoly.spieler.model.SpielerTyp;
import de.dhpoly.wuerfel.model.Wuerfel;

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
	public void verarbeiteKarte(Karte karte)
	{}

	@Override
	public int getSpielerNr()
	{
		return 0;
	}

	@Override
	public void setAktuellerSpieler(boolean isAktuell)
	{}

	@Override
	public boolean isAktuellerSpieler()
	{
		return false;
	}

	@Override
	public List<RessourcenDatensatz> getRessourcenKontoauszug()
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
	public void zeigeDatenobjekt(Datenobjekt objekt)
	{}

	@Override
	public void vergebeRessourcen(int ertrag)
	{}

	@Override
	public void setWuerfelnMoeglich(boolean value)
	{}

	@Override
	public void setWuerfelWeitergabeMoeglich(boolean value)
	{}

	@Override
	public void setSpielfeldAnsichtDaten(Optional<Fenster> fenster, List<Wuerfel> wuerfel)
	{}

	@Override
	public void kaufe(StrasseKaufen strasse)
	{}

	@Override
	public SpielerDaten getDaten()
	{
		return new SpielerDaten(SpielerTyp.LOKAL, "Typ von 'Unimplemented'");
	}

	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel) throws IOException
	{}

	@Override
	public Spiel getSpiel()
	{
		return null;
	}
}
