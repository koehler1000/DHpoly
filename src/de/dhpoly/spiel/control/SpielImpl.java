package de.dhpoly.spiel.control;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.feld.Feld;
import de.dhpoly.handel.view.HandelUI;
import de.dhpoly.karte.Karte;
import de.dhpoly.karte.control.BezahlKarte;
import de.dhpoly.karte.control.RueckenKarte;
import de.dhpoly.karte.control.WetterKarte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.kartenverbucher.control.KartenverbucherImpl;
import de.dhpoly.pause.Pause;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.view.SpielUIVerwalter;
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

	private SpielUIVerwalter uiVerwalter = new SpielUIVerwalter();

	public SpielImpl(List<Feld> felder, Einstellungen einstellungen, Wuerfel wuerfel)
	{
		this.felder = felder;
		this.einstellungen = einstellungen;
		this.wuerfel = wuerfel;
		this.aktuellerSpieler = 0;
	}

	@Override
	public void ruecke()
	{
		new Thread(this::rueckeAsync).start();
	}

	private void rueckeAsync()
	{
		for (int i = 0; i < 10; i++)
		{
			wuerfel.wuerfeln();
			Pause.pause(100, uiVerwalter);
		}

		ruecke(getAktuellerSpieler(), wuerfel.getWuerfelErgebnisSumme());
	}

	public void ruecke(Spieler spieler, int augensumme)
	{
		Thread thread = rueckeThread(spieler, augensumme);
		thread.start();
	}

	public Thread rueckeThread(Spieler spieler, int augensumme)
	{
		return new Thread(() -> {
			Feld aktuellesFeld = felder.get(spieler.getFeldNr());

			for (int i = 0; i < augensumme - 1; i++)
			{
				aktuellesFeld.verlasseFeld(spieler);
				aktuellesFeld = getNaechstesFeld(aktuellesFeld);
				aktuellesFeld.laufeUeberFeld(spieler);
				Pause.pause(200, uiVerwalter);
			}

			aktuellesFeld.verlasseFeld(spieler);
			aktuellesFeld = getNaechstesFeld(aktuellesFeld);
			aktuellesFeld.betreteFeld(spieler, augensumme, wetter);
			spieler.setFeldNr(felder.indexOf(aktuellesFeld));
		});
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

		uiVerwalter.leereRand();
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

			informiereBeobachter();
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

		uiVerwalter.createOberflaeche(spieler, this);

		informiereBeobachter();
	}

	@Override
	public Wuerfel getWuerfel()
	{
		return wuerfel;
	}

	private static final int CONST_START = 0;
	private static final int CONST_WUERFELN = 1;
	private static final int CONST_NAECHSTER_SPIELER = 2;

	private int aktuellerSchritt = CONST_START;
	private String beschreibungNaechsterSchritt = "Spiel beginnen";

	@Override
	public void naechsterSchritt()
	{
		if (aktuellerSchritt == CONST_START)
		{
			uiVerwalter.leereRand();
			beschreibungNaechsterSchritt = "Würfeln";
			aktuellerSchritt = CONST_WUERFELN;
		}
		else if (aktuellerSchritt == CONST_WUERFELN)
		{
			ruecke();
			beschreibungNaechsterSchritt = "Würfel weitergeben";
			aktuellerSchritt = CONST_NAECHSTER_SPIELER;
		}
		else if (aktuellerSchritt == CONST_NAECHSTER_SPIELER)
		{
			naechsterSpieler();
			aktuellerSchritt = CONST_START;
			naechsterSchritt();
		}
	}

	@Override
	public String getBeschreibungNaechsterSchritt()
	{
		return beschreibungNaechsterSchritt;
	}

	@Override
	public void zeigeAktuellemSpieler(String string, HandelUI handelUI)
	{
		uiVerwalter.zeigeAufRand(string, handelUI, getAktuellerSpieler());
	}

	public void setAnimationen(boolean b)
	{
		uiVerwalter.setAnimationen(false);
	}
}
