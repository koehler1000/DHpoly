package de.dhpoly.spieler.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
	private int bargeld;
	private int holzVorrat = 0;
	private int steinVorrat = 0;
	private Spiel spiel;
	private boolean aktuellerSpieler = false;
	private List<RessourcenDatensatz> vorraete = new ArrayList<RessourcenDatensatz>();

	@Deprecated
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
		vorraete.addAll(einstellungen.getSpielerStartVorraete());
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
		new StrasseKaufenUI(strasse, this);
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

	@Override
	public void einzahlen(RessourcenDatensatz datensatz)
	{
		auszahlen(new RessourcenDatensatzImpl(datensatz.getRessource(), -datensatz.getAnzahl()));
	}

	@Override
	public void auszahlen(RessourcenDatensatz datensatz)
	{
		switch (datensatz.getRessource())
		{
			case GELD:
				bargeld -= datensatz.getAnzahl();
				break;
			case HOLZ:
				holzVorrat -= datensatz.getAnzahl();
				break;
			case STEIN:
				steinVorrat -= datensatz.getAnzahl();
				break;
		}
	}

	@Override
	public void ueberweiseGeld(RessourcenDatensatz datensatz, Spieler empfaenger)
	{
		auszahlen(datensatz);
		empfaenger.einzahlen(datensatz);
	}

	@Override
	public Set<RessourcenDatensatz> getAktuelleVorraete()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RessourcenDatensatz> getRessourcenTransaktionen()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRessourcenWerte(Ressource ressource)
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
