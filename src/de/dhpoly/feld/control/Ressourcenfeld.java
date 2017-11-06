package de.dhpoly.feld.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.model.Ressource;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.spieler.Spieler;

public class Ressourcenfeld extends Observable implements Feld
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
		// TODO Auto-generated method stub
		this.spieler.add(spieler);
		setChanged();
		notifyObservers();
	}

	public Ressource getRessource()
	{
		return ressource;
	}

	@Override
	public void verlasseFeld(Spieler spieler)
	{
		this.spieler.remove(spieler);
		setChanged();
		notifyObservers();
	}

	@Override
	public List<Spieler> getSpielerAufFeld()
	{
		return spieler;
	}
}
