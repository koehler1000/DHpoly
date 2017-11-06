package de.dhpoly.spiel.control;

import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.karte.Karte;
import de.dhpoly.karte.control.BezahlKarte;
import de.dhpoly.karte.control.RueckenKarte;
import de.dhpoly.karte.control.WetterKarte;
import de.dhpoly.kartenverbucher.control.KartenverbucherImpl;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.model.Balancing;
import de.dhpoly.spieler.Spieler;

public class SpielImpl implements Spiel
{
	private List<Feld> felder;
	private List<Spieler> spieler;
	private int aktuellerSpieler;

	public SpielImpl(List<Feld> felder, List<Spieler> spieler)
	{
		this.felder = felder;
		this.spieler = spieler;
		aktuellerSpieler = 0;
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
			spieler.einzahlen(Balancing.UEBER_LOS);
		}

		felder.get(feldNrSoll).betreteFeld(spieler, augensumme);
		spieler.setFeldNr(feldNrSoll);
	}

	@Override
	public void naechsterSpieler()
	{
		if (aktuellerSpieler + 1 >= spieler.size())
		{
			aktuellerSpieler = 0;
		}
		else
		{
			aktuellerSpieler++;

		}
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
		// TODO Auto-generated method stub
	}

	private void verarbeiteKarte(WetterKarte karte)
	{
		// TODO Auto-generated method stub
	}
}
