package de.dhpoly.spiel.control;

import java.util.List;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.fehler.control.FehlerImpl;
import de.dhpoly.feld.Feld;
import de.dhpoly.karte.Karte;
import de.dhpoly.karte.control.BezahlKarte;
import de.dhpoly.karte.control.RueckenKarte;
import de.dhpoly.karte.control.WetterKarte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.kartenverbucher.control.KartenverbucherImpl;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;

public class SpielImpl implements Spiel
{
	private List<Feld> felder;
	private List<Spieler> spieler;
	private int aktuellerSpieler;
	private Wetter wetter = Wetter.BEWOELKT;
	private Einstellungen einstellungen;

	public SpielImpl(List<Feld> felder, List<Spieler> spieler, Einstellungen einstellungen)
	{
		this.felder = felder;
		this.spieler = spieler;
		this.einstellungen = einstellungen;
		aktuellerSpieler = 0;

		for (Spieler spieler2 : spieler)
		{
			felder.get(0).betreteFeld(spieler2, 0, wetter);
		}
	}

	@Override
	public void ruecke(Spieler spieler, int augenzahl1, int augenzahl2)
	{
		ruecke(spieler, augenzahl1 + augenzahl2);
	}

	@Override
	public void ruecke(Spieler spieler, int augensumme)
	{
		int feldNrSoll = spieler.getFeldNr() + augensumme;

		if (feldNrSoll >= felder.size())
		{
			feldNrSoll = feldNrSoll - felder.size(); // test
			spieler.einzahlen(einstellungen.getBetragPassierenLos());
		}

		felder.get(feldNrSoll).betreteFeld(spieler, augensumme, wetter);
		spieler.setFeldNr(feldNrSoll);
	}

	@Override
	public void naechsterSpieler()
	{
		spieler.get(aktuellerSpieler).setAkutellerSpieler(false);

		if (aktuellerSpieler + 1 >= spieler.size())
		{
			aktuellerSpieler = 0;
			vergebeRessourcen();
		}
		else
		{
			aktuellerSpieler++;
		}

		spieler.get(aktuellerSpieler).setAkutellerSpieler(true);
	}

	private void vergebeRessourcen()
	{
		// TODO Auto-generated method stub
		// jeder der Holz oder Stein-Ressourcenquellen hat, soll je nach Einstellung
		// Ressourcen erhalten
		FehlerImpl.fehlerAufgetreten(
				"SpielImpl teilt nicht die entsprechenden Ressourcen zu (siehe 'vergebeRessourcen();'");
	}

	@Override
	public Spieler getAktuellerSpieler()
	{
		return spieler.get(aktuellerSpieler);
	}

	@Override
	public void verarbeiteKarte(Karte karte)
	{
		if (karte instanceof BezahlKarte)
		{
			verarbeiteKarte((BezahlKarte) karte);
		}
		else if (karte instanceof RueckenKarte)
		{
			verarbeiteKarte((RueckenKarte) karte);
		}
		else if (karte instanceof WetterKarte)
		{
			verarbeiteKarte((WetterKarte) karte);
		}
	}

	private void verarbeiteKarte(BezahlKarte karte)
	{
		new KartenverbucherImpl().bewegeGeld(karte, spieler, spieler.get(aktuellerSpieler));
	}

	private void verarbeiteKarte(RueckenKarte karte)
	{
		new KartenverbucherImpl().bewegeSpieler(karte, spieler.get(aktuellerSpieler), wetter);
	}

	private void verarbeiteKarte(WetterKarte karte)
	{
		this.wetter = karte.getWetter();
	}

	@Override
	public int getFaktorMiete()
	{
		return wetter.getMietbeeinflussung();
	}

	@Override
	public List<Feld> getFelder()
	{
		return felder;
	}

	@Override
	public List<Spieler> getSpieler()
	{
		return spieler;
	}

	@Override
	public Wetter getWetter()
	{
		return wetter;
	}

	@Override
	public Einstellungen getEinstellungen()
	{
		return einstellungen;
	}

	@Override
	public void fuegeSpielerHinzu(Spieler spieler)
	{
		this.spieler.add(spieler);
		felder.get(0).betreteFeld(spieler, 0, wetter);
	}
}
