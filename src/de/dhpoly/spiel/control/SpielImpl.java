package de.dhpoly.spiel.control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.feld.Feld;
import de.dhpoly.fenster.view.Fenster;
import de.dhpoly.karte.Karte;
import de.dhpoly.karte.control.BezahlKarte;
import de.dhpoly.karte.control.RueckenKarte;
import de.dhpoly.karte.control.WetterKarte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.kartenverbucher.control.KartenverbucherImpl;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.wuerfel.Wuerfel;
import observerpattern.Beobachtbarer;

public class SpielImpl extends Beobachtbarer implements Spiel
{
	private List<Feld> felder;
	private List<Spieler> spieler = new ArrayList<>();
	private int aktuellerSpieler;
	private Wetter wetter = Wetter.BEWOELKT;
	private Einstellungen einstellungen;
	private Wuerfel wuerfel;

	private JPanel pnlLeer = new JPanel();

	public SpielImpl(List<Feld> felder, Einstellungen einstellungen, Wuerfel wuerfel)
	{
		this.felder = felder;
		this.einstellungen = einstellungen;
		this.wuerfel = wuerfel;
		this.aktuellerSpieler = 0;

		pnlLeer.setBackground(Fenster.getDesignfarbe());
	}

	@Override
	public void ruecke()
	{
		wuerfel.wuerfeln();
		ruecke(getAktuellerSpieler(), wuerfel.getWuerfelErgebnisSumme());
	}

	public void ruecke(Spieler spieler, int augensumme)
	{
		Thread thread = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				Feld aktuellesFeld = felder.get(spieler.getFeldNr());

				for (int i = 0; i < augensumme - 1; i++)
				{
					try
					{
						aktuellesFeld.verlasseFeld(spieler);
						aktuellesFeld = getNaechstesFeld(aktuellesFeld);
						aktuellesFeld.laufeUeberFeld(spieler);
						Thread.sleep(200);
					}
					catch (InterruptedException ex)
					{
						// ignorieren
					}
				}

				aktuellesFeld.verlasseFeld(spieler);
				aktuellesFeld = getNaechstesFeld(aktuellesFeld);
				aktuellesFeld.betreteFeld(spieler, augensumme, wetter);
				spieler.setFeldNr(felder.indexOf(aktuellesFeld));
			}
		});

		thread.start();
	}

	private Feld getNaechstesFeld(Feld feld)
	{
		int feldNr = felder.indexOf(feld);

		feldNr++;

		if (feldNr >= felder.size())
		{
			return felder.get(0);
		}
		else
		{
			return felder.get(feldNr);
		}
	}

	@Override
	public void naechsterSpieler()
	{
		Spieler spielerAktuell = spieler.get(aktuellerSpieler);
		Spiel.setPanel(pnlLeer);

		spieler.get(aktuellerSpieler).setAkutellerSpieler(false);
		pruefeVerloren(spielerAktuell);

		if (aktuellerSpieler + 1 >= spieler.size())
		{
			aktuellerSpieler = 0;
			vergebeRessourcen();
		}
		else
		{
			aktuellerSpieler++;
		}

		spieler.get(aktuellerSpieler).setAkutellerSpieler(true);
	}

	private void pruefeVerloren(Spieler spielerAktuell)
	{
		if (spielerAktuell.isNegative())
		{
			spieler.remove(spielerAktuell);
			spielerAktuell.zeigeNachrichtVerloren();

			if (spieler.size() == 1)
			{
				spieler.get(0).zeigeNachrichtGewonnen();
			}
		}
	}

	private void vergebeRessourcen()
	{
		// TODO Implementation
		// jeder der Holz oder Stein-Ressourcenquellen hat, soll je nach Einstellung
		// Ressourcen erhalten
		// FehlerImpl.fehlerAufgetreten(
		// "SpielImpl teilt nicht die entsprechenden Ressourcen zu (siehe
		// 'vergebeRessourcen();'");
	}

	@Override
	public Spieler getAktuellerSpieler()
	{
		return spieler.get(aktuellerSpieler);
	}

	@Override
	public void verarbeiteKarte(Karte karte)
	{
		if (karte instanceof BezahlKarte)
		{
			verarbeiteKarte((BezahlKarte) karte);
		}
		else if (karte instanceof RueckenKarte)
		{
			verarbeiteKarte((RueckenKarte) karte);
		}
		else if (karte instanceof WetterKarte)
		{
			verarbeiteKarte((WetterKarte) karte);
		}
	}

	private void verarbeiteKarte(BezahlKarte karte)
	{
		new KartenverbucherImpl().bewegeGeld(karte, spieler, spieler.get(aktuellerSpieler));
	}

	private void verarbeiteKarte(RueckenKarte karte)
	{
		new KartenverbucherImpl().bewegeSpieler(karte, spieler.get(aktuellerSpieler), wetter);
	}

	private void verarbeiteKarte(WetterKarte karte)
	{
		this.wetter = karte.getWetter();
	}

	@Override
	public double getFaktorMiete()
	{
		return wetter.getMietbeeinflussung();
	}

	@Override
	public List<Feld> getFelder()
	{
		return felder;
	}

	@Override
	public List<Spieler> getSpieler()
	{
		return spieler;
	}

	@Override
	public Wetter getWetter()
	{
		return wetter;
	}

	@Override
	public Einstellungen getEinstellungen()
	{
		return einstellungen;
	}

	@Override
	public void fuegeSpielerHinzu(Spieler spieler)
	{
		spieler.setAkutellerSpieler(this.spieler.isEmpty());

		spieler.setSpielerNr(this.spieler.size());
		this.spieler.add(spieler);
		felder.get(0).betreteFeld(spieler, 0, wetter);
	}

	@Override
	public Wuerfel getWuerfel()
	{
		return wuerfel;
	}

	private int aktuellerSchritt = -1;
	private String beschreibungNaechsterSchritt = "Spiel beginnen";

	@Override
	public void naechsterSchritt()
	{
		aktuellerSchritt++;

		switch (aktuellerSchritt)
		{
			case 0:
				beschreibungNaechsterSchritt = "Würfeln";
				break;
			case 1:
				ruecke();
				beschreibungNaechsterSchritt = "Würfel weitergeben";
				break;
			case 2:
				naechsterSpieler();
				aktuellerSchritt = 0;
				beschreibungNaechsterSchritt = "Würfeln";
				break;
		}
	}

	@Override
	public String getBeschreibungNaechsterSchritt()
	{
		return beschreibungNaechsterSchritt;
	}
	//
	// private JPanel pnlInhalt;
	//
	// @Override
	// public static void setPanel(JPanel pnl)
	// {
	// pnlInhalt = pnl;
	// informiereBeobachter();
	// }
	//
	// public JPanel getPanel()
	// {
	// return pnlInhalt;
	// }
}
