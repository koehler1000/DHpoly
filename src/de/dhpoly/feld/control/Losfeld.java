package de.dhpoly.feld.control;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.feld.Feld;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.spieler.Spieler;

public class Losfeld extends Feld
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
		spieler.einzahlen(einstellungen.getBetragBetretenLos());
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
