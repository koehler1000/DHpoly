package de.dhpoly.spielfeld;

import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.spieler.Spieler;

public class Spiel
{
	private List<Feld> felder;
	private List<Spieler> spieler;
	private int aktuellerSpieler;

	public Spiel(List<Feld> felder, List<Spieler> spieler)
	{
		this.felder = felder;
		this.spieler = spieler;
		aktuellerSpieler = 0;
	}

	public void ruecke(Spieler spieler, int augenzahl1, int augenzahl2)
	{
		ruecke(spieler, augenzahl1 + augenzahl2);
	}

	public void ruecke(Spieler spieler, int augensumme)
	{
		int feldNrSoll = spieler.getFeldNr() + augensumme;

		if (feldNrSoll >= felder.size())
		{
			feldNrSoll = feldNrSoll - felder.size(); //test
		}

		felder.get(feldNrSoll).betreteFeld(spieler, augensumme);
		spieler.setFeldNr(feldNrSoll);
	}
	
	public void naechsterSpieler(){
		if(aktuellerSpieler + 1 >= spieler.size()){
			aktuellerSpieler = 0;
		}
		else{
			aktuellerSpieler++;

		}
	}

	public Spieler getAktuellerSpieler() { 
		return spieler.get(aktuellerSpieler);
	}
}
