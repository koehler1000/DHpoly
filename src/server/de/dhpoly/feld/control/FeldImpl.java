package de.dhpoly.feld.control;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.model.Spieler;

public abstract class FeldImpl implements Feld
{
	private String beschriftung;
	private List<Spieler> spielerAufFeld = new ArrayList<>();

	public String getBeschriftung()
	{
		return beschriftung;
	}

	public FeldImpl(String beschriftung)
	{
		this.beschriftung = beschriftung;
	}

	public void betreteFeld(Spieler spieler, int augensumme, Spiel spiel)
	{
		spielerAufFeld.add(spieler);

		// wird z.B. beim Spielstart nicht ausgef�hrt
		if (augensumme > 0)
		{
			spielerBetrittFeld(spieler, augensumme, spiel);
		}
	}

	public void verlasseFeld(Spieler spieler)
	{
		spielerAufFeld.remove(spieler);
		spielerVerlaesstFeld(spieler);
	}

	protected void spielerBetrittFeld(Spieler spieler, int augensumme, Spiel spiel)
	{
		// leer in der Standardimplementierung, kann in Implementierung �berschrieben
		// werden
	}

	protected void spielerVerlaesstFeld(Spieler spieler)
	{
		// leer in der Standardimplementierung, kann in Implementierung �berschrieben
		// werden
	}

	public List<Spieler> getSpielerAufFeld()
	{
		return spielerAufFeld;
	}

	public boolean isKaufbar()
	{
		return false;
	}

	public void laufeUeberFeld(Spieler spieler)
	{
		spielerAufFeld.add(spieler);
	}
}
