package de.dhpoly.spiel.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.fehler.control.TelegamBenachrichtiger;
import de.dhpoly.fehler.model.Fehler;
import de.dhpoly.fehler.model.FehlerTyp;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.FeldStrasse;
import de.dhpoly.feld.model.StrasseKaufen;
import de.dhpoly.feld.model.StrasseKaufenStatus;
import de.dhpoly.handel.Handel;
import de.dhpoly.karte.model.BezahlKarte;
import de.dhpoly.karte.model.Karte;
import de.dhpoly.karte.model.RueckenKarte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.karte.model.WetterKarte;
import de.dhpoly.kartenverbucher.control.KartenverbucherImpl;
import de.dhpoly.logik.Logik;
import de.dhpoly.nachricht.control.NachrichtVerwalter;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.model.SpielStatus;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerComputer;
import de.dhpoly.spieler.control.SpielerLokal;
import de.dhpoly.spieler.model.SpielerDaten;
import de.dhpoly.spieler.model.SpielerStatus;
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

	private boolean aktuellerSpielerHatGewuerfelt = false;
	private boolean aktuellerSpielerIstGerueckt = false;

	List<Class<? extends Logik>> logikverwalter = new ArrayList<>();

	private SpielStatus status = SpielStatus.SPIEL_VORBEREITUNG;

	public SpielImpl()
	{
		felder = new ArrayList<>();
		spieler = new ArrayList<>();
		wetter = Wetter.BEWOELKT;
		einstellungen = new Einstellungen();
		wuerfelPaar = new WuerfelpaarImpl();

		logikverwalter.add(TelegamBenachrichtiger.class);
		logikverwalter.add(Handel.class);
		logikverwalter.add(NachrichtVerwalter.class);
	}

	@Override
	public void ruecke()
	{
		getAktuellerSpieler().setWuerfelnMoeglich(false);
		new Thread(this::rueckeAsync).start();
	}

	private void rueckeAsync()
	{
		wuerfelPaar.wuerfeln();
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
			Feld aktuellesFeld = felder.get(spieler.getDaten().getFeldNr());

			for (int i = 1; i < augensumme; i++)
			{
				aktuellesFeld.verlasseFeld(spieler);
				aktuellesFeld = getNaechstesFeld(aktuellesFeld);
				aktuellesFeld.laufeUeberFeld(spieler);
			}

			aktuellesFeld.verlasseFeld(spieler);
			aktuellesFeld = getNaechstesFeld(aktuellesFeld);
			aktuellesFeld.betreteFeld(spieler, augensumme, wetter);
			spieler.getDaten().setFeldNr(felder.indexOf(aktuellesFeld));

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

	private void pruefeVerloren(Spieler spielerAktuell)
	{
		if (spielerAktuell.getDaten().getRessourcenWert(Ressource.GELD) < 0
				|| spielerAktuell.getDaten().getStatus() == SpielerStatus.VERLOREN)
		{
			spielerAusscheidenLassen(spielerAktuell);

			Nachricht nachricht = new Nachricht(spielerAktuell.getDaten().getName() + " hat verloren");
			zeigeAllenSpielern(nachricht);

			if (spieler.size() == 1)
			{
				Spieler sieger = spieler.get(0);
				sieger.getDaten().setSpielerStatus(SpielerStatus.GEWONNEN);

				Nachricht nachrichtGewonnen = new Nachricht(sieger.getDaten().getName() + " hat gewonnen");
				zeigeAllenSpielern(nachrichtGewonnen);
			}
		}
	}

	private void spielerAusscheidenLassen(Spieler spieler)
	{
		this.spieler.remove(spieler);

		// Felder zurueckgeben
		List<Feld> felder = getFelder(spieler);
		while (!felder.isEmpty())
		{
			Feld feld = felder.get(0);
			if (feld instanceof FeldStrasse)
			{
				FeldStrasse strasse = (FeldStrasse) feld;
				strasse.zurueckgeben();
			}
		}

		// Spielerstatus setzen
		spieler.getDaten().setSpielerStatus(SpielerStatus.VERLOREN);
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
		spieler.getDaten().setAktuellerSpieler(this.spieler.isEmpty());
		spieler.getDaten().setSpielerNr(this.spieler.size());
		this.spieler.add(spieler);
		this.spielerImSpiel.add(spieler);
		felder.get(0).betreteFeld(spieler, 0, wetter);
	}

	@Override
	public void fuegeLokalenSpielerHinzu(String spielerName)
	{
		fuegeSpielerHinzu(new SpielerLokal(spielerName, this));
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
			sp.getDaten().einzahlen(einstellungen.getSpielerStartVorraete());
		}

		getAktuellerSpieler().setWuerfelnMoeglich(true);
	}

	@Override
	public List<Feld> getFelder(Spieler spieler)
	{
		List<Feld> felderSpieler = new ArrayList<>();
		for (Feld feld : felder)
		{
			if (feld.gehoertSpieler(spieler.getDaten()))
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
		spielerAktuellAlt.getDaten().setAktuellerSpieler(false);

		spielerImSpiel.remove(spielerAktuellAlt);
		pruefeVerloren(spielerAktuellAlt);
		if (spielerAktuellAlt.getDaten().getStatus() != SpielerStatus.VERLOREN)
		{
			spielerImSpiel.add(spielerAktuellAlt);
		}

		if (spieler.get(0) == spielerAktuellAlt)
		{
			// Überrundung
			vergebeRessourcen();
		}

		Spieler spielerAktuellNeu = spielerImSpiel.get(0);
		spielerAktuellNeu.getDaten().setAktuellerSpieler(true);
		spielerAktuellNeu.setWuerfelnMoeglich(true);

		setAktuellerSpielerHatGewuerfelt(false);
		setAktuellerSpielerIstGerueckt(false);
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
		if (strasse.isKaufbar() && sp.getDaten().kannBezahlen(strasse.getKaufpreis()))
		{
			sp.getDaten().auszahlen(strasse.getKaufpreis());
			strasse.setEigentuemer(sp.getDaten());
			strasse.setStatus(StrasseKaufenStatus.ANGENOMMEN);
		}

		zeigeAllenSpielern(strasse.getStrasse());
	}

	@Override
	public void empfange(Datenobjekt objekt)
	{
		logikverwalter.forEach(c -> empfange(c, objekt));
	}

	private void empfange(Class<? extends Logik> c, Datenobjekt objekt)
	{
		try
		{
			c.newInstance().verarbeite(objekt, this);
		}
		catch (InstantiationException | IllegalAccessException ex)
		{
			TelegamBenachrichtiger benachrichtiger = new TelegamBenachrichtiger();
			try
			{
				benachrichtiger.sendTelegramMessage("Fehler", ex.getMessage());
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

	@Override
	public Optional<Spieler> getSpieler(SpielerDaten spielerDaten)
	{
		return spieler.stream().findFirst().filter(s -> s.getDaten() == spielerDaten);
	}

	@Override
	public Optional<Spieler> getSpieler(Optional<SpielerDaten> eigentuemer)
	{
		if (eigentuemer.isPresent())
		{
			return getSpieler(eigentuemer.get());
		}
		return Optional.empty();
	}
}
