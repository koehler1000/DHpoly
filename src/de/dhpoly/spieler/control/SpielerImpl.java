package de.dhpoly.spieler.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.Ressourcenfeld;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import observerpattern.Beobachtbarer;
import observerpattern.Beobachter;

public abstract class SpielerImpl extends Beobachtbarer implements Spieler
{
	int feldNr = 0;
	String name;
	Spiel spiel;
	boolean aktuellerSpieler = false;
	int spielerNr;
	boolean verloren = false;

	private List<RessourcenDatensatz> verlauf = new ArrayList<>();

	public SpielerImpl(String name, Spiel spiel)
	{
		this.name = name;
		this.spiel = spiel;
	}

	@Override
	public int getSpielerNr()
	{
		return spielerNr;
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

	public boolean isNegative()
	{
		return getRessourcenWerte(Ressource.GELD) < 0;
	}

	@Override
	public void setAkutellerSpieler(boolean isAktuell)
	{
		aktuellerSpieler = isAktuell;
		setAktuellerSpielerAbstract(isAktuell);
		informiereBeobachter();
	}

	abstract void setAktuellerSpielerAbstract(boolean isAktuell);

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
		RessourcenDatensatz satz = new RessourcenDatensatz(datensatz.getRessource(), 0 - datensatz.getAnzahl(),
				datensatz.getBeschreibung());
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

	@Override
	public void einzahlen(List<RessourcenDatensatz> datensaetze)
	{
		for (RessourcenDatensatz ressourcenDatensatz : datensaetze)
		{
			einzahlen(ressourcenDatensatz);
		}
	}

	@Override
	public void auszahlen(List<RessourcenDatensatz> datensaetze)
	{
		for (RessourcenDatensatz ressourcenDatensatz : datensaetze)
		{
			auszahlen(ressourcenDatensatz);
		}
	}

	@Override
	public boolean kannBezahlen(List<RessourcenDatensatz> kostenHaus)
	{
		for (RessourcenDatensatz ressourcenDatensatz : kostenHaus)
		{
			if (getRessourcenWerte(ressourcenDatensatz.getRessource()) < ressourcenDatensatz.getAnzahl())
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public void ausscheiden()
	{
		strassenZurueckgeben();
		verloren = true;
		informiereBeobachter();
	}

	protected void strassenZurueckgeben()
	{
		List<Feld> felder = spiel.getFelder(this);
		while (!felder.isEmpty())
		{
			Feld feld = felder.get(0);
			if (feld instanceof Strasse)
			{
				Strasse strasse = (Strasse) feld;
				strasse.zurueckgeben();
			}
		}
	}

	@Override
	public void gewonnen()
	{
		zeigeDatenobjekt(new Nachricht("Gewonnen"));
	}

	@Override
	public List<Feld> getFelder()
	{
		if (Optional.ofNullable(spiel).isPresent())
		{
			return spiel.getFelder(this);
		}
		else
		{
			return new ArrayList<>();
		}
	}

	@Override
	public void setSpielerNr(int nr)
	{
		spielerNr = nr;
	}

	public boolean hatVerloren()
	{
		return verloren;
	}

	@Override
	public void zeigeTransaktionErfolgreich(Transaktion transaktion)
	{
		String nachrichtentext = "Handel mit " + transaktion.getHandelspartner().getName() + " wurde angenommen";
		Nachricht nachricht = new Nachricht(nachrichtentext);
		zeigeDatenobjekt(nachricht);
	}

	@Override
	public void zeigeTransaktionGescheitert(Transaktion transaktion)
	{
		String nachrichtentext = "Handel mit " + transaktion.getHandelspartner().getName() + " wurde abgelehnt";
		Nachricht nachricht = new Nachricht(nachrichtentext);
		zeigeDatenobjekt(nachricht);
	}

	@Override
	public void vergebeRessourcen(int ertrag)
	{
		for (Feld feld : spiel.getFelder(this))
		{
			if (feld instanceof Ressourcenfeld)
			{
				Ressourcenfeld ressourcenfeld = (Ressourcenfeld) feld;
				RessourcenDatensatz res = new RessourcenDatensatz(ressourcenfeld.getRessource(), ertrag);

				einzahlen(res);
			}
		}
	}
}
