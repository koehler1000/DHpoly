package de.dhpoly.feld.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import de.dhpoly.feld.Feld;
import de.dhpoly.karte.Karte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.kartenstapel.Kartenstapel;
import de.dhpoly.spieler.Spieler;

public class Ereignisfeld extends Observable implements Feld
{
	private Kartenstapel kartenstapel;
	private List<Spieler> spieler = new ArrayList<>();

	public Ereignisfeld(Kartenstapel kartenstapel)
	{
		this.kartenstapel = kartenstapel;
	}

	@Override
	public String getBeschriftung()
	{
		return "Ereignisfeld";
	}

	@Override
	public void betreteFeld(Spieler spieler, int augensumme, Wetter aktuellesWetter)
	{
		Karte karte = kartenstapel.ziehen();
		spieler.zeigeKarte(karte);
		this.spieler.add(spieler);
		setChanged();
		notifyObservers();
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
