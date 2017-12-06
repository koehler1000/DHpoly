package de.dhpoly.spieler.control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.feld.view.StrasseKaufenUI;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.handel.view.HandelUI;
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
	private List<Feld> felder = new ArrayList<>();

	// mit vorverkauften Strassen
	public SpielerImpl(String name, Einstellungen einstellungen, Spiel spiel, List<Feld> felder)
	{
		this(name, einstellungen, spiel);
		this.felder = felder;
	}

	public SpielerImpl(String name, Einstellungen einstellungen, Spiel spiel)
	{
		this.name = name;
		this.spiel = spiel;

		for (RessourcenDatensatz ressourcenDatensatz : einstellungen.getSpielerStartVorraete())
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

	public boolean isNegative()
	{
		return getRessourcenWerte(Ressource.GELD) < 0;
	}

	@Override
	public void zeigeTransaktionsvorschlag(Transaktion transaktion)
	{
		JFrame frame = new JFrame("Handelsangebot");
		frame.add(new HandelUI(transaktion.getAnbietender(), transaktion.getHandelspartner(), transaktion, frame));
		frame.setSize(1000, 1000);
		frame.setVisible(true);
	}

	@Override
	public void zeigeKaufmoeglichkeit(Strasse strasse)
	{
		if (strasse.getKaufpreis() <= getRessourcenWerte(Ressource.GELD))
		{
			new StrasseKaufenUI(strasse, this);
		}
	}

	@Override
	public void zeigeKarte(Karte karte)
	{
		spiel.verarbeiteKarte(karte);
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
		RessourcenDatensatz satz = new RessourcenDatensatzImpl(datensatz.getRessource(), 0 - datensatz.getAnzahl(),
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
	public void zeigeNachrichtVerloren()
	{
		JOptionPane.showMessageDialog(null, name + " hat verloren");
	}

	@Override
	public void zeigeNachrichtGewonnen()
	{
		JOptionPane.showMessageDialog(null, name + " hat gewonnen");
	}

	@Override
	public void feldHinzu(Feld feld)
	{
		felder.add(feld);
	}

	@Override
	public void feldWeg(Feld feld)
	{
		felder.remove(feld);
	}

	@Override
	public List<Feld> getFelder()
	{
		return felder;
	}
}
