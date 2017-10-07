package de.dhpoly.spielfeld;

import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.spieler.Spieler;

public class Spiel
{
	List<Feld> felder;
	List<Spieler> spieler;

	public Spiel(List<Feld> felder)
	{
		this.felder = felder;
	}

	public void ruecke(Spieler spieler, int augenzahl1, int augenzahl2)
	{
		ruecke(spieler, augenzahl1 + augenzahl2);
	}

	public void ruecke(Spieler spieler, int augensumme)
	{
		int feldNrSoll = spieler.getFeldNr() + augensumme;

		if (felder.size() >= feldNrSoll)
		{
			feldNrSoll = feldNrSoll - felder.size();
		}

		felder.get(feldNrSoll).betreteFeld(spieler, augensumme);
		spieler.setFeldNr(feldNrSoll);
	}
}
