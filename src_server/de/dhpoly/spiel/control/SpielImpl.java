package de.dhpoly.spiel.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.fehler.control.FehlerLogikImpl;
import de.dhpoly.fehler.model.Fehler;
import de.dhpoly.fehler.model.FehlerTyp;
import de.dhpoly.feld.control.FeldEreignis;
import de.dhpoly.feld.control.FeldLos;
import de.dhpoly.feld.control.FeldRessource;
import de.dhpoly.feld.control.FeldStrasse;
import de.dhpoly.feld.control.StrasseKaufenLogik;
import de.dhpoly.feld.model.FeldDaten;
import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.handel.control.HandelImpl;
import de.dhpoly.karte.model.BezahlKarte;
import de.dhpoly.karte.model.Karte;
import de.dhpoly.karte.model.RueckenKarte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.karte.model.WetterKarte;
import de.dhpoly.kartenstapel.Kartenstapel;
import de.dhpoly.kartenstapel.control.KartenstapelImpl;
import de.dhpoly.kartenverbucher.control.KartenverbucherImpl;
import de.dhpoly.logik.Logik;
import de.dhpoly.nachricht.control.NachrichtLogikImpl;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.netzwerk.NetzwerkServer;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.model.SpielStatus;
import de.dhpoly.spiel.model.SpielfeldDaten;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.model.SpielerStatus;
import de.dhpoly.spieler.model.SpielerTyp;
import de.dhpoly.spielfeld.model.RessourcenfeldDaten;
import de.dhpoly.spielfeld.model.Standardspielfeld;
import de.dhpoly.wuerfel.Wuerfelpaar;
import de.dhpoly.wuerfel.control.WuerfelAufrufLogik;
import de.dhpoly.wuerfel.control.WuerfelWeitergabeLogik;
import de.dhpoly.wuerfel.control.WuerfelpaarImpl;
import de.dhpoly.wuerfel.model.WuerfelDaten;

public class SpielImpl implements Spiel
{
	private SpielfeldDaten felder;

	private List<Spieler> spieler = new ArrayList<>();
	private List<Spieler> spielerImSpiel = new ArrayList<>();
	private Wetter wetter = Wetter.BEWOELKT;
	private Einstellungen einstellungen;
	private Wuerfelpaar wuerfelPaar;

	private Kartenstapel kartenstapel;

	private boolean aktuellerSpielerHatGewuerfelt = false;
	private boolean aktuellerSpielerIstGerueckt = false;

	List<Class<? extends Logik>> logikverwalter = new ArrayList<>();

	private SpielStatus status = SpielStatus.SPIEL_VORBEREITUNG;

	private Optional<NetzwerkServer> server = Optional.empty();

	private List<StrasseDaten> aktuelleKaufangebote = new ArrayList<>();

	public SpielImpl()
	{
		einstellungen = new Einstellungen();
		setFelder(new Standardspielfeld().getStandardSpielfeld(einstellungen));
		spieler = new ArrayList<>();
		wetter = Wetter.BEWOELKT;
		wuerfelPaar = new WuerfelpaarImpl();
		kartenstapel = new KartenstapelImpl(einstellungen.getEreigniskarten());

		logikverwalter.add(FehlerLogikImpl.class);
		logikverwalter.add(HandelImpl.class);
		logikverwalter.add(NachrichtLogikImpl.class);
		logikverwalter.add(WuerfelAufrufLogik.class);
		logikverwalter.add(WuerfelWeitergabeLogik.class);
		logikverwalter.add(SpielStartLogik.class);
		logikverwalter.add(StrasseKaufenLogik.class);
		logikverwalter.add(SpielerHinzufuegenLogik.class);

		setFelder(new Standardspielfeld().getStandardSpielfeld(einstellungen));
	}

	public SpielImpl(NetzwerkServer server)
	{
		this();
		this.server = Optional.ofNullable(server);
		server.setDatenobjektverwalter(this);
	}

	@Override
	public void ruecke()
	{
		if (status == SpielStatus.SPIEL_LAEUFT)
		{
			if (aktuellerSpielerHatGewuerfelt)
			{
				Fehler fehler = new Fehler("Spieler hat bereits gewürfelt", FehlerTyp.FEHLER_SPIELER);
				verarbeiteFehler(fehler);
			}
			else
			{
				wuerfelPaar.wuerfeln();
				setAktuellerSpielerHatGewuerfelt(true);
				ruecke(getAktuellerSpieler(), wuerfelPaar.berechneWuerfelSumme());
				server.ifPresent(s -> s.sendeAnSpieler(new WuerfelDaten(wuerfelPaar.getWuerfel())));
			}
		}
		else
		{
			Fehler fehler = new Fehler("Spiel noch nicht gestartet", FehlerTyp.FEHLER_SPIELER);
			verarbeiteFehler(fehler);
		}
	}

	public void ruecke(Spieler spieler, int augensumme)
	{
		FeldDaten aktuellesFeld = felder.get(spieler.getFeldNr());
		aktuellesFeld.spielerEntfernen(spieler);

		for (int i = 1; i < augensumme; i++)
		{
			aktuellesFeld = getNaechstesFeld(aktuellesFeld);
		}

		aktuellesFeld = getNaechstesFeld(aktuellesFeld);
		aktuellesFeld.spielerHinzu(spieler);

		switch (aktuellesFeld.getTyp())
		{
			case EREIGNISFELD:
				FeldEreignis feldEreignis = new FeldEreignis(kartenstapel);
				feldEreignis.betreteFeld(spieler, augensumme, this);
				break;
			case LOS:
				FeldLos feldLos = new FeldLos(einstellungen);
				feldLos.betreteFeld(spieler, augensumme, this);
				break;
			case RESSOURCE:
				FeldRessource feldRessource = new FeldRessource((RessourcenfeldDaten) aktuellesFeld, einstellungen);
				feldRessource.betreteFeld(spieler, augensumme, this);
				break;
			case STRASSE:
				FeldStrasse feldStrasse = new FeldStrasse((StrasseDaten) aktuellesFeld);
				feldStrasse.betreteFeld(spieler, augensumme, this);
				break;
			default:
				break;
		}

		spieler.setFeldNr(felder.indexOf(aktuellesFeld));
		aktuellerSpielerIstGerueckt = true;
		zeigeAllenSpielern(felder);
	}

	private FeldDaten getNaechstesFeld(FeldDaten feld)
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
		if (spielerAktuell.getRessourcenWert(Ressource.GELD) < 0
				|| spielerAktuell.getStatus() == SpielerStatus.VERLOREN)
		{
			spielerAusscheidenLassen(spielerAktuell);

			Nachricht nachricht = new Nachricht(spielerAktuell.getName() + " hat verloren");
			zeigeAllenSpielern(nachricht);

			if (spieler.size() == 1)
			{
				Spieler sieger = spieler.get(0);
				sieger.setSpielerStatus(SpielerStatus.GEWONNEN);

				Nachricht nachrichtGewonnen = new Nachricht(sieger.getName() + " hat gewonnen");
				zeigeAllenSpielern(nachrichtGewonnen);
			}
		}
	}

	private void spielerAusscheidenLassen(Spieler spieler)
	{
		this.spieler.remove(spieler);

		List<StrasseDaten> strassen = spieler.getStrassen();
		for (StrasseDaten strasse : strassen)
		{
			strasseZurueckgeben(strasse);
		}

		// Spielerstatus setzen
		spieler.setSpielerStatus(SpielerStatus.VERLOREN);
	}

	private void strasseZurueckgeben(StrasseDaten strasse)
	{
		strasse.setEigentuemer(Optional.empty());
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
		new KartenverbucherImpl().bewegeSpieler(karte, getAktuellerSpieler(), this);
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
	public List<FeldDaten> getFelder()
	{
		return felder.getFelder();
	}

	@Override
	public List<Spieler> getSpieler()
	{
		return spieler;
	}

	@Override
	public void fuegeSpielerHinzu(Spieler spieler)
	{
		if (status == SpielStatus.SPIEL_VORBEREITUNG)
		{
			spieler.setAktuellerSpieler(this.spieler.isEmpty());
			spieler.setSpielerNr(this.spieler.size());
			this.spieler.add(spieler);
			this.spielerImSpiel.add(spieler);
			FeldDaten feld = felder.get(0);
			feld.spielerHinzu(spieler);
		}
	}

	@Override
	public void fuegeLokalenSpielerHinzu(String spielerName)
	{
		fuegeSpielerHinzu(new Spieler(SpielerTyp.LOKAL, spielerName));
	}

	@Override
	public void verarbeiteFehler(Fehler fehler)
	{
		if (fehler.getFehlertyp().isAlleSpielerInformieren())
		{
			zeigeAllenSpielern(fehler);
		}
		else if (fehler.getFehlertyp().isAktuellenSpielerInformieren())
		{
			zeigeSpieler(getAktuellerSpieler(), fehler);
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
			new FehlerLogikImpl().sendTelegramMessage(fehler.getTitel(), fehler.getFehlertext());
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
	public void setFelder(List<FeldDaten> felder)
	{
		if (status == SpielStatus.SPIEL_VORBEREITUNG)
		{
			this.felder = new SpielfeldDaten(felder);
			aktualisiereGruppen(felder);
		}
	}

	private void aktualisiereGruppen(List<FeldDaten> felder2)
	{
		Map<Integer, Integer> gruppenHaeufigkeit = new HashMap<>();
		List<StrasseDaten> strassen = new ArrayList<>();
		felder2.stream().filter(e -> (e instanceof StrasseDaten)).forEach(s -> strassen.add((StrasseDaten) s));

		for (StrasseDaten strasse : strassen)
		{
			int gruppe = strasse.getGruppe();
			int alterWert = Optional.ofNullable(gruppenHaeufigkeit.get(gruppe)).orElse(0);

			gruppenHaeufigkeit.put(gruppe, alterWert + 1);
		}

		strassen.forEach(e -> e.setStrassenAnzahlInGruppe(gruppenHaeufigkeit.get(e.getGruppe())));
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
			this.kartenstapel = new KartenstapelImpl(einstellungen.getEreigniskarten());
		}
	}

	@Override
	public void starteSpiel()
	{
		status = SpielStatus.SPIEL_LAEUFT;

		for (Spieler sp : spieler)
		{
			sp.einzahlen(einstellungen.getSpielerStartVorraete());
			server.ifPresent(s -> s.sendeAnSpieler(sp));
		}

		server.ifPresent(s -> s.sendeAnSpieler(felder));
	}

	public List<FeldDaten> getStrassen(Spieler spieler)
	{
		List<FeldDaten> felderSpieler = new ArrayList<>();
		for (FeldDaten feld : felder.getFelder())
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
		this.fuegeSpielerHinzu(new Spieler(SpielerTyp.COMPUTER, text));
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
		spielerAktuellAlt.setAktuellerSpieler(false);

		spielerImSpiel.remove(spielerAktuellAlt);
		pruefeVerloren(spielerAktuellAlt);
		if (spielerAktuellAlt.getStatus() != SpielerStatus.VERLOREN)
		{
			spielerImSpiel.add(spielerAktuellAlt);
		}

		Spieler spielerAktuellNeu = spielerImSpiel.get(0);
		spielerAktuellNeu.setAktuellerSpieler(true);

		setAktuellerSpielerHatGewuerfelt(false);
		setAktuellerSpielerIstGerueckt(false);

		server.ifPresent(s -> s.sendeAnSpieler(spielerAktuellAlt));
		server.ifPresent(s -> s.sendeAnSpieler(spielerAktuellNeu));
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
			aktuelleKaufangebote = new ArrayList<>();
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
			FehlerLogikImpl benachrichtiger = new FehlerLogikImpl();
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
	public void zeigeSpieler(Spieler sp, Datenobjekt datenobjekt)
	{
		server.ifPresent(s -> s.sendeAnSpieler(datenobjekt, sp));
	}

	@Override
	public void zeigeAllenSpielern(Datenobjekt datenobjekt)
	{
		server.ifPresent(s -> s.sendeAnSpieler(datenobjekt));
	}

	@Override
	public SpielfeldDaten getSpielfeld()
	{
		return felder;
	}

	@Override
	public boolean kannSpielerStrasseKaufen(Spieler spieler, StrasseDaten strasse)
	{
		if (spieler != getAktuellerSpieler())
		{
			return false;
		}

		return aktuelleKaufangebote.contains(strasse);
	}

	@Override
	public void fuegeStrassenKaufHinzu(StrasseDaten strasse)
	{
		aktuelleKaufangebote.add(strasse);
	}
}
