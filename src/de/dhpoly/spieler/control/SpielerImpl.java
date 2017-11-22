package de.dhpoly.spieler.control;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.feld.view.StrasseKaufenUI;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.karte.Karte;
import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.control.RessourcenDatensatzImpl;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import observerpattern.Beobachtbarer;
import observerpattern.Beobachter;

public class SpielerImpl extends Beobachtbarer implements Spieler
{
	private int feldNr = 0;
	private String name;
	private Spiel spiel;
	private boolean aktuellerSpieler = false;
	private List<RessourcenDatensatz> verlauf = new ArrayList<RessourcenDatensatz>();

	@Deprecated
	public SpielerImpl(String name, int startguthaben, Spiel spiel)
	{
		this.name = name;
		this.spiel = spiel;
		einzahlen(new RessourcenDatensatzImpl(Ressource.GELD, startguthaben));
	}

	public SpielerImpl(String name, Einstellungen einstellungen, Spiel spiel)
	{
		this.name = name;
		this.spiel = spiel;

		for (RessourcenDatensatz ressourcenDatensatz : verlauf)
		{
			einzahlen(ressourcenDatensatz);
		}
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

	@Deprecated
	public int getBargeld()
	{
		return getRessourcenWerte(Ressource.GELD);
	}

	@Deprecated
	public void einzahlen(int betrag)
	{
		einzahlen(new RessourcenDatensatzImpl(Ressource.GELD, betrag));
	}

	@Deprecated
	public void auszahlen(int betrag)
	{
		auszahlen(new RessourcenDatensatzImpl(Ressource.GELD, betrag));
	}

	public boolean isNegative()
	{
		return getRessourcenWerte(Ressource.GELD) >= 0;
	}

	@Override
	public void zeigeTransaktionsvorschlag(Transaktion transaktion)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void zeigeKaufmoeglichkeit(Strasse strasse)
	{
		new StrasseKaufenUI(strasse, this);
	}

	@Override
	public void zeigeKarte(Karte karte)
	{
		spiel.verarbeiteKarte(karte);
	}

	@Deprecated
	@Override
	public void ueberweiseGeld(int betrag, Spieler empfaenger)
	{
		empfaenger.einzahlen(betrag);
		this.auszahlen(betrag);
	}

	@Override
	public int getSteinVorrat()
	{
		return getRessourcenWerte(Ressource.STEIN);
	}

	@Override
	public int getHolzVorrat()
	{
		return getRessourcenWerte(Ressource.HOLZ);
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

	@Override
	public void einzahlen(RessourcenDatensatz datensatz)
	{
		verlauf.add(datensatz);
		informiereBeobachter();
	}

	@Override
	public void auszahlen(RessourcenDatensatz datensatz)
	{
		RessourcenDatensatz satz = new RessourcenDatensatzImpl(datensatz.getRessource(), 0 - datensatz.getAnzahl());
		verlauf.add(satz);
		informiereBeobachter();
	}

	@Override
	public void ueberweise(RessourcenDatensatz datensatz, Spieler empfaenger)
	{
		auszahlen(datensatz);
		empfaenger.einzahlen(datensatz);
	}

	@Override
	public List<RessourcenDatensatz> getRessourcenTransaktionen()
	{
		return verlauf;
	}

	public int getRessourcenWerte(Ressource ressource)
	{
		int i = 0;
		for (RessourcenDatensatz ressourcenDatensatz : verlauf)
		{
			if (ressourcenDatensatz.getRessource() == ressource)
			{
				i += ressourcenDatensatz.getAnzahl();
			}
		}
		return i;
	}
}
