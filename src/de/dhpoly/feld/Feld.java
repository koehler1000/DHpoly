package de.dhpoly.feld;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.karte.model.Wetter;
import de.dhpoly.spieler.Spieler;
import observerpattern.Beobachtbarer;

public abstract class Feld extends Beobachtbarer
{
	private List<Spieler> spielerAufFeld = new ArrayList<>();

	public abstract String getBeschriftung();

	public void betreteFeld(Spieler spieler, int augensumme, Wetter aktuellesWetter)
	{
		spielerAufFeld.add(spieler);
		spielerBetrittFeld(spieler, augensumme, aktuellesWetter);

		informiereBeobachter();
	}

	public void verlasseFeld(Spieler spieler)
	{
		spielerAufFeld.remove(spieler);
		spielerVerlaesstFeld(spieler);

		informiereBeobachter();
	}

	private void spielerBetrittFeld(Spieler spieler, int augensumme, Wetter aktuellesWetter)
	{
		// leer in der Standardimplementierung, kann in Implementierung überschrieben
		// werden
	}

	private void spielerVerlaesstFeld(Spieler spieler)
	{
		// leer in der Standardimplementierung, kann in Implementierung überschrieben
		// werden
	}

	public List<Spieler> getSpielerAufFeld()
	{
		return spielerAufFeld;
	}

	public boolean gehoertSpieler(Spieler spieler)
	{
		return false;
	}

	public boolean isKaufbar()
	{
		return false;
	}
}
