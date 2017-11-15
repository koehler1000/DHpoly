package de.dhpoly.spieler.control;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.karte.Karte;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import observerpattern.Beobachtbarer;
import observerpattern.Beobachter;

public class SpielerImpl extends Beobachtbarer implements Spieler
{
	private int feldNr = 0;
	private String name;
	private int bargeld;
	private int holzVorrat = 0;
	private int steinVorrat = 0;
	private Spiel spiel;
	private boolean aktuellerSpieler = false;

	public SpielerImpl(String name, int startguthaben, Spiel spiel)
	{
		this.name = name;
		this.spiel = spiel;
		bargeld = startguthaben;
	}

	public SpielerImpl(String name, Einstellungen einstellungen, Spiel spiel)
	{
		this.name = name;
		this.spiel = spiel;
		bargeld = einstellungen.getStartguthaben();
	}

	@Override
	public int getSpielerNr()
	{
		if (spiel != null)
		{
			return spiel.getSpieler().indexOf(this);
		}
		else
		{
			// für Testzwecke
			return 0;
		}
	}

	public int getFeldNr()
	{
		return feldNr;
	}

	public String getName()
	{
		return name;
	}

	public void setFeldNr(int feldNrSoll)
	{
		this.feldNr = feldNrSoll;
	}

	public int getBargeld()
	{
		return bargeld;
	}

	public void einzahlen(int betrag)
	{
		bargeld += betrag;
		informiereBeobachter();
	}

	public void auszahlen(int betrag)
	{
		bargeld -= betrag;
		informiereBeobachter();
	}

	public boolean isNegative()
	{
		return bargeld >= 0;
	}

	@Override
	public void zeigeTransaktionsvorschlag(Transaktion transaktion)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void zeigeKaufmoeglichkeit(Strasse strasse)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void zeigeKarte(Karte karte)
	{
		spiel.verarbeiteKarte(karte);
	}

	@Override
	public void ueberweiseGeld(int betrag, Spieler empfaenger)
	{
		empfaenger.einzahlen(betrag);
		this.auszahlen(betrag);
	}

	@Override
	public int getSteinVorrat()
	{
		return steinVorrat;
	}

	@Override
	public int getHolzVorrat()
	{
		return holzVorrat;
	}

	@Override
	public void setAkutellerSpieler(boolean isAktuell)
	{
		aktuellerSpieler = isAktuell;
		informiereBeobachter();
	}

	@Override
	public boolean isAktuellerSpieler()
	{
		return aktuellerSpieler;
	}

	@Override
	public void addBeobachterHinzu(Beobachter beobachter)
	{
		addBeobachter(beobachter);
	}

}
