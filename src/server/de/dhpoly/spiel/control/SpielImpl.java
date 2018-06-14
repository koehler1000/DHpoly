package de.dhpoly.spiel.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.empfaenger.model.Empfaenger;
import de.dhpoly.feld.control.FeldEreignis;
import de.dhpoly.feld.control.FeldLos;
import de.dhpoly.feld.control.FeldRessource;
import de.dhpoly.feld.control.FeldStrasse;
import de.dhpoly.feld.control.HausbauLogik;
import de.dhpoly.feld.control.StrasseKaufenLogik;
import de.dhpoly.feld.model.FeldDaten;
import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.handel.control.HandelLogik;
import de.dhpoly.karte.model.BezahlKarte;
import de.dhpoly.karte.model.Karte;
import de.dhpoly.karte.model.RueckenKarte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.karte.model.WetterKarte;
import de.dhpoly.kartenstapel.Kartenstapel;
import de.dhpoly.kartenstapel.control.KartenstapelImpl;
import de.dhpoly.kartenverbucher.control.KartenverbucherImpl;
import de.dhpoly.logik.Logik;
import de.dhpoly.nachricht.control.SpielerInformierenLogikImpl;
import de.dhpoly.nachricht.control.TelegramNachrichtLogikImpl;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.netzwerk.NetzwerkServer;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.model.SpielStatus;
import de.dhpoly.spiel.model.SpielfeldDaten;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.model.SpielerStatus;
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

	private List<Class<? extends Logik>> logikverwalter = new ArrayList<>();

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

		logikverwalter.add(TelegramNachrichtLogikImpl.class);
		logikverwalter.add(HandelLogik.class);
		logikverwalter.add(SpielerInformierenLogikImpl.class);
		logikverwalter.add(WuerfelAufrufLogik.class);
		logikverwalter.add(WuerfelWeitergabeLogik.class);
		logikverwalter.add(SpielStartLogik.class);
		logikverwalter.add(StrasseKaufenLogik.class);
		logikverwalter.add(SpielerHinzufuegenLogik.class);
		logikverwalter.add(HausbauLogik.class);

		setFelder(new Standardspielfeld().getStandardSpielfeld(einstellungen));
	}

	public SpielImpl(NetzwerkServer server)
	{
		this();
		server.setDatenobjektverwalter(this);
		this.server = Optional.ofNullable(server);
	}

	@Override
	public void ruecke()
	{
		if (status == SpielStatus.SPIEL_LAEUFT)
		{
			if (getAktuellerSpieler().getStatus() == SpielerStatus.MUSS_WUERFELN)
			{
				wuerfelPaar.wuerfeln();
				ruecke(getAktuellerSpieler(), wuerfelPaar.berechneWuerfelSumme());

				if (!wuerfelPaar.isPasch())
				{
					getAktuellerSpieler().setSpielerStatus(SpielerStatus.MUSS_WUERFEL_WEITERGEBEN);
				}
				zeigeSpieler(getAktuellerSpieler(), getAktuellerSpieler());
				zeigeAllenSpielern(new WuerfelDaten(wuerfelPaar.getWuerfel()));
				zeigeAllenSpielern(felder);
			}
			else
			{
				Nachricht fehler = new Nachricht("Spieler hat bereits gewürfelt", Empfaenger.AKTUELLER_SPIELER);
				zeigeSpieler(getAktuellerSpieler(), fehler);
			}
		}
		else
		{
			Nachricht fehler = new Nachricht("Spiel noch nicht gestartet", Empfaenger.AKTUELLER_SPIELER);
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

	private boolean hatVerloren(Spieler spielerAktuell)
	{
		return (spielerAktuell.getRessourcenWert(Ressource.GELD) < 0
				|| spielerAktuell.getStatus() == SpielerStatus.VERLOREN);
	}

	private void lasseSpielerVerlieren(Spieler spielerAktuell)
	{
		spielerAusscheidenLassen(spielerAktuell);

		Nachricht nachricht = new Nachricht(spielerAktuell.getName() + " hat verloren", Empfaenger.ALLE);
		zeigeAllenSpielern(nachricht);

		if (spieler.size() == 1)
		{
			Spieler sieger = spieler.get(0);
			sieger.setSpielerStatus(SpielerStatus.GEWONNEN);

			Nachricht nachrichtGewonnen = new Nachricht(sieger.getName() + " hat gewonnen", Empfaenger.ALLE);
			zeigeAllenSpielern(nachrichtGewonnen);
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
		fuegeSpielerHinzu(new Spieler(spielerName));
	}

	@Override
	public void verarbeiteFehler(Nachricht fehler)
	{
		if (fehler.getEmpfaenger().isAlleSpielerInformieren())
		{
			zeigeAllenSpielern(fehler);
		}
		else if (fehler.getEmpfaenger().isAktuellenSpielerInformieren())
		{
			zeigeSpieler(getAktuellerSpieler(), fehler);
		}

		if (fehler.getEmpfaenger().isEntwicklerInformieren())
		{
			informiereEntwickler(fehler);
		}
	}

	private void informiereEntwickler(Nachricht fehler)
	{
		try
		{
			new TelegramNachrichtLogikImpl().sendTelegramMessage(fehler.getTitel(), fehler.getText());
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

		getAktuellerSpieler().setSpielerStatus(SpielerStatus.MUSS_WUERFELN);

		for (Spieler sp : spieler)
		{
			sp.einzahlen(einstellungen.getSpielerStartVorraete());
			zeigeAllenSpielern(sp);
		}

		zeigeAllenSpielern(felder);
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
		this.fuegeSpielerHinzu(new Spieler(text));
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
		spielerAktuellAlt.setSpielerStatus(SpielerStatus.WARTET);

		spielerImSpiel.remove(spielerAktuellAlt);

		if (hatVerloren(spielerAktuellAlt))
		{
			lasseSpielerVerlieren(spielerAktuellAlt);
		}
		else
		{
			spielerImSpiel.add(spielerAktuellAlt);
		}

		Spieler spielerAktuellNeu = spielerImSpiel.get(0);
		if (spieler.size() == 1)
		{
			lasseSpielerGewinnen(spielerAktuellNeu);
		}
		else
		{
			spielerAktuellNeu.setSpielerStatus(SpielerStatus.MUSS_WUERFELN);
		}

		zeigeAllenSpielern(spielerAktuellAlt);
		zeigeAllenSpielern(spielerAktuellNeu);
	}

	private void lasseSpielerGewinnen(Spieler sp)
	{
		sp.setSpielerStatus(SpielerStatus.GEWONNEN);
		Nachricht nachricht = new Nachricht(sp.getName() + " hat gewonnen.", Empfaenger.ALLE_SPIELER);
		empfange(nachricht);
	}

	@Override
	public void wuerfelWeitergeben(Spieler spieler)
	{
		if (spieler.getStatus() == SpielerStatus.MUSS_WUERFEL_WEITERGEBEN)
		{
			if (spieler == getAktuellerSpieler())
			{
				aktuelleKaufangebote = new ArrayList<>();
				naechsterSpieler();
			}
			else
			{
				Nachricht nachricht = new Nachricht("Sie sind nicht an der Reihe", Empfaenger.AKTUELLER_SPIELER);
				empfange(nachricht);
			}
		}
		else
		{
			Nachricht nachricht = new Nachricht("Sie müssen zuerst würfeln", Empfaenger.AKTUELLER_SPIELER);
			empfange(nachricht);
		}
	}

	@Override
	public boolean kannWuerfeln(Spieler spieler)
	{
		return status == SpielStatus.SPIEL_LAEUFT && getAktuellerSpieler().getStatus() == SpielerStatus.MUSS_WUERFELN;
	}

	@Override
	public boolean kannWuerfelWeitergeben(Spieler spieler)
	{
		return status == SpielStatus.SPIEL_LAEUFT
				&& getAktuellerSpieler().getStatus() == SpielerStatus.MUSS_WUERFEL_WEITERGEBEN;
	}

	@Override
	public void empfange(Datenobjekt objekt)
	{
		System.out.println("Z 512: Nachricht bei Spiel angekommen");
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
			TelegramNachrichtLogikImpl benachrichtiger = new TelegramNachrichtLogikImpl();
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

	@Override
	public void bewege(int anzahl, Spieler spieler)
	{
		ruecke(spieler, anzahl);
	}
}
