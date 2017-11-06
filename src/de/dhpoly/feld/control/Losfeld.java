package de.dhpoly.feld.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.feld.Feld;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.spieler.Spieler;

public class Losfeld extends Observable implements Feld
{
	private Einstellungen einstellungen;
	private List<Spieler> spieler = new ArrayList<>();

	public Losfeld(Einstellungen einstellungenImpl)
	{
		this.einstellungen = einstellungenImpl;
	}

	@Override
	public String getBeschriftung()
	{
		return "Los";
	}

	@Override
	public void betreteFeld(Spieler spieler, int augensumme, Wetter aktuellesWetter)
	{
		if (augensumme > 2)
		{
			spieler.einzahlen(einstellungen.getBetragBetretenLos());
		}
		this.spieler.add(spieler);
		setChanged();
		notifyObservers();
	}

	@Override
	public void verlasseFeld(Spieler spieler)
	{
		this.spieler.remove(spieler);
		System.out.println("Los verlassen");
		setChanged();
		notifyObservers();
	}

	@Override
	public List<Spieler> getSpielerAufFeld()
	{
		return spieler;
	}
}
