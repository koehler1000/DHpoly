package de.dhpoly.feld.control;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.model.Ressource;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.spieler.Spieler;

public class Ressourcenfeld extends Feld
{
	private Ressource ressource;
	private List<Spieler> spieler = new ArrayList<>();

	public Ressourcenfeld(Ressource ressourcentyp)
	{
		this.ressource = ressourcentyp;
	}

	@Override
	public String getBeschriftung()
	{
		return "Ressource";
	}

	@Override
	public void betreteFeld(Spieler spieler, int augensumme, Wetter aktuellesWetter)
	{
		// TODO Was passiert, wenn Ressourcenfelder betreten werden?

		this.spieler.add(spieler);
		informiereBeobachter();
	}

	public Ressource getRessource()
	{
		return ressource;
	}

	@Override
	public void verlasseFeld(Spieler spieler)
	{
		this.spieler.remove(spieler);
		informiereBeobachter();
	}

	@Override
	public List<Spieler> getSpielerAufFeld()
	{
		return spieler;
	}
}
