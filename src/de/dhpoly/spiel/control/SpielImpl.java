package de.dhpoly.spiel.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.fehler.control.TelegamBenachrichtiger;
import de.dhpoly.fehler.model.Fehler;
import de.dhpoly.feld.Feld;
import de.dhpoly.karte.Karte;
import de.dhpoly.karte.control.BezahlKarte;
import de.dhpoly.karte.control.RueckenKarte;
import de.dhpoly.karte.control.WetterKarte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.kartenverbucher.control.KartenverbucherImpl;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.pause.Pause;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.wuerfel.Wuerfelpaar;
import de.dhpoly.wuerfel.control.Wuerfel;
import observerpattern.Beobachtbarer;

public class SpielImpl extends Beobachtbarer implements Spiel
{
	private List<Feld> felder;
	private List<Spieler> spieler = new ArrayList<>();
	private int aktuellerSpieler;
	private Wetter wetter = Wetter.BEWOELKT;
	private Einstellungen einstellungen;
	private Wuerfelpaar wuerfel;

	private Fenster fenster;

	private boolean animationen = true;

	public SpielImpl(List<Feld> felder, Einstellungen einstellungen, Wuerfelpaar wuerfel)
	{
		this.felder = felder;
		this.einstellungen = einstellungen;
		this.wuerfel = wuerfel;
		this.aktuellerSpieler = 0;

		this.fenster = new Fenster(new Bilderverwalter());
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
			Pause.pause(100, animationen);
		}

		ruecke(getAktuellerSpieler(), wuerfel.berechneWuerfelSumme());
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
				Pause.pause(200, animationen);
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
		Spieler spielerAktuellAlt = spieler.get(aktuellerSpieler);

		spielerAktuellAlt.setAkutellerSpieler(false);
		pruefeVerloren(spielerAktuellAlt);

		if (aktuellerSpieler + 1 >= spieler.size())
		{
			aktuellerSpieler = 0;
			vergebeRessourcen();
		}
		else
		{
			aktuellerSpieler++;
		}

		Spieler spielerAktuellNeu = spieler.get(aktuellerSpieler);
		spielerAktuellNeu.setAkutellerSpieler(true);

		leereRand();
		zeigeAktuellenSpieler();
	}

	private void zeigeAktuellenSpieler()
	{
		fenster.zeigeTab(spieler.get(aktuellerSpieler).getName());
	}

	private void leereRand()
	{
		for (Spieler sp : spieler)
		{
			sp.leereRand();
		}
	}

	private void pruefeVerloren(Spieler spielerAktuell)
	{
		if (spielerAktuell.isNegative() || spielerAktuell.hatVerloren())
		{
			spieler.remove(spielerAktuell);
			spielerAktuell.ausscheiden();

			Nachricht nachricht = new Nachricht(spielerAktuell.getName() + " hat verloren");
			zeigeAllenSpielern(nachricht);

			if (spieler.size() == 1)
			{
				Spieler sieger = spieler.get(0);
				sieger.gewonnen();

				Nachricht nachrichtGewonnen = new Nachricht(sieger.getName() + " hat gewonnen");
				zeigeAllenSpielern(nachrichtGewonnen);
			}

			informiereBeobachter();
		}
	}

	private void zeigeAllenSpielern(Datenobjekt objekt)
	{
		for (Spieler sp : spieler)
		{
			sp.zeigeDatenobjekt(objekt);
		}
	}

	private void vergebeRessourcen()
	{
		// TODO Implementation
		// jeder der Holz oder Stein-Ressourcenquellen hat, soll je nach Einstellung
		// Ressourcen erhalten
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

		createOberflaeche(spieler, this);

		informiereBeobachter();
	}

	private void createOberflaeche(Spieler spieler, Spiel spiel)
	{
		SpielfeldAnsicht ansicht = new SpielfeldAnsicht(spiel, spieler);
		fenster.zeigeSpielansicht(ansicht, spieler.getName());
		spieler.setSpielfeldAnsicht(ansicht);
	}

	@Override
	public List<Wuerfel> getWuerfel()
	{
		return wuerfel.getWuerfel();
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
			leereRand();
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

	public void setAnimationen(boolean b)
	{
		animationen = b;
	}

	@Override
	public void verarbeiteFehler(Fehler fehler)
	{
		if (fehler.getFehlertyp().isAlleSpielerInformieren())
		{
			spieler.forEach(e -> e.zeigeDatenobjekt(fehler));
		}
		else if (fehler.getFehlertyp().isAktuellenSpielerInformieren())
		{
			spieler.get(aktuellerSpieler).zeigeDatenobjekt(fehler);
		}

		if (fehler.getFehlertyp().isEntwicklerInformieren())
		{
			informiereEntwickler(fehler);
		}
	}

	private void informiereEntwickler(Fehler fehler)
	{
		try
		{
			TelegamBenachrichtiger.sendTelegramMessage(fehler.getTitel(), fehler.getFehlertext());
		}
		catch (IOException ex)
		{
			// ignorieren
		}
	}
}
