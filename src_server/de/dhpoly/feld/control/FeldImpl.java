package de.dhpoly.feld.control;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.Feld;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;

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

		// wird z.B. beim Spielstart nicht ausgeführt
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
		// leer in der Standardimplementierung, kann in Implementierung überschrieben
		// werden
	}

	protected void spielerVerlaesstFeld(Spieler spieler)
	{
		// leer in der Standardimplementierung, kann in Implementierung überschrieben
		// werden
	}

	public List<Spieler> getSpielerAufFeld()
	{
		return spielerAufFeld;
	}

	public abstract boolean gehoertSpieler(Spieler spieler);

	public boolean isKaufbar()
	{
		return false;
	}

	public void laufeUeberFeld(Spieler spieler)
	{
		spielerAufFeld.add(spieler);
	}

	@Override
	public void verarbeite(Datenobjekt objekt, Spiel spiel)
	{
		if (objekt instanceof Transaktion)
		{
			Transaktion transaktion = (Transaktion) objekt;
			if (transaktion.getFelderEigentumswechsel().contains(this))
			{
				// TODO Transaktion
			}
		}
	}
}
