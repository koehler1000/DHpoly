package de.dhpoly.spiel.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.fehler.control.TelegamBenachrichtiger;
import de.dhpoly.fehler.model.Fehler;
import de.dhpoly.fehler.model.FehlerTyp;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.model.StrasseKaufen;
import de.dhpoly.feld.model.StrasseKaufenStatus;
import de.dhpoly.handel.Handel;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.karte.model.BezahlKarte;
import de.dhpoly.karte.model.Karte;
import de.dhpoly.karte.model.RueckenKarte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.karte.model.WetterKarte;
import de.dhpoly.kartenverbucher.control.KartenverbucherImpl;
import de.dhpoly.logik.Logik;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.pause.Pause;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.model.SpielStatus;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerComputer;
import de.dhpoly.spieler.control.SpielerLokal;
import de.dhpoly.spieler.model.SpielerDaten;
import de.dhpoly.wuerfel.Wuerfelpaar;
import de.dhpoly.wuerfel.control.WuerfelpaarImpl;

public class SpielImpl implements Spiel
{
	private List<Feld> felder;
	private List<Spieler> spieler = new ArrayList<>();
	private List<Spieler> spielerImSpiel = new ArrayList<>();
	private Wetter wetter = Wetter.BEWOELKT;
	private Einstellungen einstellungen;
	private Wuerfelpaar wuerfelPaar;

	@Deprecated
	private Optional<Fenster> fenster;

	private boolean animationen = true;

	private boolean aktuellerSpielerHatGewuerfelt = false;
	private boolean aktuellerSpielerIstGerueckt = false;

	private Map<Class<? extends Datenobjekt>, Class<? extends Logik>> map = new HashMap<>();

	private SpielStatus status = SpielStatus.SPIEL_VORBEREITUNG;

	public SpielImpl()
	{
		felder = new ArrayList<>();
		spieler = new ArrayList<>();
		wetter = Wetter.BEWOELKT;
		einstellungen = new Einstellungen();
		wuerfelPaar = new WuerfelpaarImpl();

		this.fenster = Optional.empty();

		map.put(Fehler.class, TelegamBenachrichtiger.class);
		map.put(Transaktion.class, Handel.class);
	}

	@Override
	public void ruecke()
	{
		getAktuellerSpieler().setWuerfelnMoeglich(false);
		new Thread(this::rueckeAsync).start();
	}

	private void rueckeAsync()
	{
		for (int i = 0; i < 10; i++)
		{
			wuerfelPaar.wuerfeln();
			Pause.pause(100, animationen);
		}
		setAktuellerSpielerHatGewuerfelt(true);

		ruecke(getAktuellerSpieler(), wuerfelPaar.berechneWuerfelSumme());
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

			aktuellerSpielerIstGerueckt = true;
			spieler.setWuerfelWeitergabeMoeglich(true);
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

	private void zeigeAktuellenSpieler()
	{
		fenster.ifPresent(f -> f.zeigeTab(spielerImSpiel.get(0).getName()));
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
		for (Spieler sp : spieler)
		{
			sp.vergebeRessourcen(einstellungen.getRessourcenErtrag());
		}
	}

	@Override
	public Spieler getAktuellerSpieler()
	{
		return spielerImSpiel.get(0);
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
		new KartenverbucherImpl().bewegeGeld(karte, spieler, getAktuellerSpieler());
		zeigeAllenSpielern(karte);
	}

	private void verarbeiteKarte(RueckenKarte karte)
	{
		new KartenverbucherImpl().bewegeSpieler(karte, getAktuellerSpieler(), wetter);
		zeigeAllenSpielern(karte);
	}

	private void verarbeiteKarte(WetterKarte karte)
	{
		this.wetter = karte.getWetter();
		zeigeAllenSpielern(karte);
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
	public void fuegeSpielerHinzu(Spieler spieler)
	{
		spieler.setAktuellerSpieler(this.spieler.isEmpty());
		spieler.setSpielerNr(this.spieler.size());
		this.spieler.add(spieler);
		this.spielerImSpiel.add(spieler);
		felder.get(0).betreteFeld(spieler, 0, wetter);

		createOberflaeche(spieler);
	}

	@Override
	public void fuegeLokalenSpielerHinzu(String spielerName)
	{
		fuegeSpielerHinzu(new SpielerLokal(spielerName, this));
	}

	private void createOberflaeche(Spieler spieler)
	{
		spieler.setSpielfeldAnsichtDaten(fenster, wuerfelPaar.getWuerfel());
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
			getAktuellerSpieler().zeigeDatenobjekt(fehler);
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
			new TelegamBenachrichtiger().sendTelegramMessage(fehler.getTitel(), fehler.getFehlertext());
		}
		catch (IOException ex)
		{
			// ignorieren
		}
	}

	@Override
	public SpielStatus getStatus()
	{
		return status;
	}

	@Override
	public void setWuerfelPaar(Wuerfelpaar wuerfelPaar)
	{
		if (status == SpielStatus.SPIEL_VORBEREITUNG)
		{
			this.wuerfelPaar = wuerfelPaar;
		}
	}

	@Override
	public void setFenster(Fenster fenster)
	{
		this.fenster = Optional.ofNullable(fenster);
	}

	@Override
	public boolean isAnimationen()
	{
		return animationen;
	}

	@Override
	public void setFelder(List<Feld> felder)
	{
		if (status == SpielStatus.SPIEL_VORBEREITUNG)
		{
			this.felder = felder;
		}
	}

	@Override
	public void setWetter(Wetter wetter)
	{
		this.wetter = wetter;
	}

	@Override
	public void setEinstellungen(Einstellungen einstellungen)
	{
		if (status == SpielStatus.SPIEL_VORBEREITUNG)
		{
			this.einstellungen = einstellungen;
		}
	}

	@Override
	public void starteSpiel()
	{
		status = SpielStatus.SPIEL_LAEUFT;

		for (Spieler sp : spieler)
		{
			sp.einzahlen(einstellungen.getSpielerStartVorraete());
		}

		getAktuellerSpieler().setWuerfelnMoeglich(true);
	}

	@Override
	public List<Feld> getFelder(Spieler spieler)
	{
		List<Feld> felderSpieler = new ArrayList<>();
		for (Feld feld : felder)
		{
			if (feld.gehoertSpieler(spieler))
			{
				felderSpieler.add(feld);
			}
		}

		return felderSpieler;
	}

	@Override
	public void fuegeComputerSpielerHinzu(String text)
	{
		this.fuegeSpielerHinzu(new SpielerComputer(text, this));
	}

	@Override
	public void wuerfeln(Spieler spieler)
	{
		if (spieler == getAktuellerSpieler())
		{
			ruecke();
		}
	}

	private void naechsterSpieler()
	{
		Spieler spielerAktuellAlt = getAktuellerSpieler();
		spielerAktuellAlt.setWuerfelWeitergabeMoeglich(false);
		spielerAktuellAlt.setWuerfelnMoeglich(false);
		spielerAktuellAlt.setAktuellerSpieler(false);

		spielerImSpiel.remove(spielerAktuellAlt);
		pruefeVerloren(spielerAktuellAlt);
		if (!spielerAktuellAlt.hatVerloren())
		{
			spielerImSpiel.add(spielerAktuellAlt);
		}

		if (spieler.get(0) == spielerAktuellAlt)
		{
			// Überrundung
			vergebeRessourcen();
		}

		Spieler spielerAktuellNeu = spielerImSpiel.get(0);
		spielerAktuellNeu.setAktuellerSpieler(true);
		spielerAktuellNeu.setWuerfelnMoeglich(true);

		setAktuellerSpielerHatGewuerfelt(false);
		setAktuellerSpielerIstGerueckt(false);

		zeigeAktuellenSpieler();
	}

	private void setAktuellerSpielerIstGerueckt(boolean b)
	{
		aktuellerSpielerIstGerueckt = b;
	}

	@Override
	public void wuerfelWeitergeben(Spieler spieler)
	{
		if (spieler == getAktuellerSpieler())
		{
			naechsterSpieler();
		}
	}

	private void setAktuellerSpielerHatGewuerfelt(boolean wert)
	{
		aktuellerSpielerHatGewuerfelt = wert;
	}

	@Override
	public boolean kannWuerfeln(Spieler spieler)
	{
		return status == SpielStatus.SPIEL_LAEUFT && !aktuellerSpielerHatGewuerfelt;
	}

	@Override
	public boolean kannWuerfelWeitergeben(Spieler spieler)
	{
		return status == SpielStatus.SPIEL_LAEUFT && aktuellerSpielerIstGerueckt;
	}

	@Override
	public void kaufe(StrasseKaufen strasse, Spieler spieler)
	{
		if (getAktuellerSpieler() == spieler)
		{
			kaufAbwickeln(strasse, spieler);
		}
		else
		{
			spieler.zeigeDatenobjekt(new Fehler("Kauf fehlgeschlagen: Nicht an der Reihe", FehlerTyp.FEHLER_SPIELER));
		}
	}

	private void kaufAbwickeln(StrasseKaufen strasse, Spieler sp)
	{
		if (strasse.isKaufbar() && sp.kannBezahlen(strasse.getKaufpreis()))
		{
			sp.auszahlen(strasse.getKaufpreis());
			strasse.setEigentuemer(sp);
			strasse.setStatus(StrasseKaufenStatus.ANGENOMMEN);
		}

		zeigeAllenSpielern(strasse.getStrasse());
	}

	@Override
	public void empfange(Datenobjekt objekt)
	{
		try
		{
			map.get(objekt.getClass()).newInstance().verarbeite(objekt, this);
		}
		catch (IOException | InstantiationException | IllegalAccessException ex)
		{
			try
			{
				TelegamBenachrichtiger benachrichtiger = new TelegamBenachrichtiger();
				benachrichtiger.verarbeite(new Fehler("FEHLER" + ex.getMessage(), FehlerTyp.FEHLER_ENTWICKLER), this);
			}
			catch (IOException ex1)
			{
				// ignorieren
			}
		}
	}

	@Override
	public Wetter getWetter()
	{
		return wetter;
	}

	@Override
	public SpielerDaten getAktuellerSpielerDaten()
	{
		return getAktuellerSpieler().getDaten();
	}
}
