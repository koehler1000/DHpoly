package de.dhpoly.feld.control;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.karte.Karte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.kartenstapel.Kartenstapel;
import de.dhpoly.spieler.Spieler;

public class Ereignisfeld extends Feld
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

		informiereBeobachter();
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
