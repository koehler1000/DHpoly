package de.dhpoly.feld.control;

import de.dhpoly.feld.Feld;
import de.dhpoly.spieler.Spieler;

public class Ressourcenfeld implements Feld
{

	@Override
	public String getBeschriftung()
	{
		return "Ressource";
	}

	@Override
	public void betreteFeld(Spieler spieler, int augensumme)
	{
		// TODO Auto-generated method stub

	}

}
