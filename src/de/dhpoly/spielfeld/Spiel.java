package de.dhpoly.spielfeld;

import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.player.Player;

public class Spiel
{
	private List<Feld> felder;
	private List<Player> spieler;
	private int aktuellerSpieler;

	public Spiel(List<Feld> felder, List<Player> spieler)
	{
		this.felder = felder;
		this.spieler = spieler;
		aktuellerSpieler = 0;
	}

	public void ruecke(Player spieler, int augenzahl1, int augenzahl2)
	{
		ruecke(spieler, augenzahl1 + augenzahl2);
	}

	public void ruecke(Player spieler, int augensumme)
	{
		int feldNrSoll = spieler.getFeldNr() + augensumme;

		if (feldNrSoll >= felder.size())
		{
			feldNrSoll = feldNrSoll - felder.size();
		}

		felder.get(feldNrSoll).betreteFeld(spieler, augensumme);
		spieler.setFeldNr(feldNrSoll);
	}
	
	public void naechsterSpieler(){
		if(spieler.size() - 1 == aktuellerSpieler){
			aktuellerSpieler = 0;
		}
		else{
			aktuellerSpieler = aktuellerSpieler++;
		}
	}

	public int getAktuellerSpieler() { 
		return aktuellerSpieler;
	}
}
