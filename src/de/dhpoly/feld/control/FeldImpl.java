package de.dhpoly.feld.control;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.spieler.Spieler;
import observerpattern.Beobachtbarer;

public abstract class FeldImpl extends Beobachtbarer implements Feld
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

	protected void spielerBetrittFeld(Spieler spieler, int augensumme, Wetter aktuellesWetter)
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

	public boolean gehoertSpieler(Spieler spieler)
	{
		return false;
	}

	public boolean isKaufbar()
	{
		return false;
	}

	public void laufeUeberFeld(Spieler spieler)
	{
		Thread thread = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				spielerAufFeld.add(spieler);
				informiereBeobachter();

				try
				{
					Thread.sleep(100);
				}
				catch (InterruptedException ex)
				{
					// ignorieren
				}

				spielerAufFeld.remove(spieler);
				informiereBeobachter();
			}
		});

		thread.start();
	}
}
