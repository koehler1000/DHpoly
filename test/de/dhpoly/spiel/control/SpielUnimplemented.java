package de.dhpoly.spiel.control;

import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.fehler.model.Fehler;
import de.dhpoly.feld.model.FeldDaten;
import de.dhpoly.feld.model.StrasseKaufen;
import de.dhpoly.karte.model.Karte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.model.SpielStatus;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.wuerfel.Wuerfelpaar;

public abstract class SpielUnimplemented implements Spiel
{
	@Override
	public void empfange(Datenobjekt objekt)
	{}

	@Override
	public Spieler getAktuellerSpieler()
	{
		return null;
	}

	@Override
	public List<Spieler> getSpieler()
	{
		return null;
	}

	@Override
	public List<FeldDaten> getFelder()
	{
		return null;
	}

	@Override
	public double getFaktorMiete()
	{
		return 0;
	}

	@Override
	public void ruecke()
	{}

	@Override
	public void verarbeiteKarte(Karte karte)
	{}

	@Override
	public void fuegeSpielerHinzu(Spieler spieler)
	{}

	@Override
	public void fuegeLokalenSpielerHinzu(String spielerName)
	{}

	@Override
	public void fuegeComputerSpielerHinzu(String text)
	{}

	@Override
	public void wuerfeln(Spieler spieler)
	{}

	@Override
	public void wuerfelWeitergeben(Spieler spieler)
	{}

	@Override
	public void verarbeiteFehler(Fehler fehler)
	{}

	@Override
	public SpielStatus getStatus()
	{
		return null;
	}

	@Override
	public void starteSpiel()
	{}

	@Override
	public void setWuerfelPaar(Wuerfelpaar wuerfelPaar)
	{}

	@Override
	public void setFelder(List<FeldDaten> felder)
	{}

	@Override
	public void setWetter(Wetter wetter)
	{}

	@Override
	public void setEinstellungen(Einstellungen einstellungen)
	{}

	@Override
	public List<FeldDaten> getStrassen(Spieler spieler)
	{
		return null;
	}

	@Override
	public boolean kannWuerfeln(Spieler spieler)
	{
		return false;
	}

	@Override
	public boolean kannWuerfelWeitergeben(Spieler spieler)
	{
		return false;
	}

	@Override
	public void kaufe(StrasseKaufen strasse, Spieler spieler)
	{}

	@Override
	public Wetter getWetter()
	{
		return null;
	}

	@Override
	public void zeigeAllenSpielern(Datenobjekt objekt)
	{}

	@Override
	public void zeigeSpieler(Spieler sp, Datenobjekt objekt)
	{}
}
