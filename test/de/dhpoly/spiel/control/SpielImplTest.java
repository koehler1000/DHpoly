package de.dhpoly.spiel.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import de.dhpoly.einstellungen.model.EinstellungenImpl;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.LosfeldTest;
import de.dhpoly.feld.control.Ressourcenfeld;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.feld.control.StrasseTest;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.karte.Karte;
import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImplTest;
import de.dhpoly.wuerfel.control.WuerfelImpl;
import observerpattern.Beobachter;

public class SpielImplTest
{
	private SpielImpl spiel;

	@Before
	public void vorbereitung()
	{
		List<Feld> felder = new ArrayList<>();
		felder.add(LosfeldTest.getDefaultFeld());
		felder.add(StrasseTest.getDefaultStrasse());
		spiel = new SpielImpl(felder, new EinstellungenImpl(), new WuerfelImpl());
		spiel.fuegeSpielerHinzu(SpielerImplTest.getDefaultSpieler("Test1", spiel));
		spiel.fuegeSpielerHinzu(SpielerImplTest.getDefaultSpieler("Test2", spiel));
	}

	@Test
	public void testaktuellerSpieler()
	{
		spiel.getAktuellerSpieler();
		assertEquals("Test1", spiel.getAktuellerSpieler().getName());
	}

	@Test
	public void testnaechsterSpieler()
	{
		spiel.naechsterSpieler();
		assertEquals("Test2", spiel.getAktuellerSpieler().getName());
	}

	@Test
	public void geldBeiUeberLos() throws InterruptedException
	{
		int geldVorDemLaufen = spiel.getAktuellerSpieler().getRessourcenWerte(Ressource.GELD);

		Thread thread = spiel.rueckeThread(spiel.getAktuellerSpieler(), 2);
		thread.start();
		thread.join();

		assertThat(spiel.getAktuellerSpieler().getRessourcenWerte(Ressource.GELD),
				Is.is(geldVorDemLaufen + new EinstellungenImpl().getBetragPassierenLos()));
	}

	@Test
	public void ressourcenJedeRunde()
	{
		List<Feld> felder = new ArrayList<>();
		felder.add(StrasseTest.getDefaultStrasse());
		felder.add(StrasseTest.getDefaultStrasse());
		Ressourcenfeld feld = new Ressourcenfeld(Ressource.HOLZ);
		felder.add(feld);
		Ressourcenfeld feld2 = new Ressourcenfeld(Ressource.STEIN);
		felder.add(feld2);
		spiel = new SpielImpl(felder, new EinstellungenImpl(), new WuerfelImpl());
		spiel.fuegeSpielerHinzu(SpielerImplTest.getDefaultSpieler("Test1", spiel));
		spiel.fuegeSpielerHinzu(SpielerImplTest.getDefaultSpieler("Test2", spiel));

		int holzVorErstemSpieler = spiel.getAktuellerSpieler().getRessourcenWerte(Ressource.HOLZ);
		int steinVorErstemSpieler = spiel.getAktuellerSpieler().getRessourcenWerte(Ressource.STEIN);

		for (int i = 0; i < spiel.getSpieler().size(); i++)
		{
			spiel.naechsterSpieler();
		}

		assertThat(spiel.getAktuellerSpieler().getRessourcenWerte(Ressource.HOLZ),
				Is.is(holzVorErstemSpieler + new EinstellungenImpl().getRessourcenErtrag()));
		assertThat(spiel.getAktuellerSpieler().getRessourcenWerte(Ressource.STEIN),
				Is.is(steinVorErstemSpieler + new EinstellungenImpl().getRessourcenErtrag()));
	}

	boolean hatVerloren = false;

	@Test
	public void spielerVerliertWennErAmEndeDesZugesKeinGeldMehrHat()
	{
		List<Feld> felder = new ArrayList<>();
		felder.add(StrasseTest.getDefaultStrasse());
		Spiel spiel = new SpielImpl(felder, new EinstellungenImpl(), new WuerfelImpl());

		spiel.fuegeSpielerHinzu(getSpieler(false));
		spiel.fuegeSpielerHinzu(getSpieler(true));

		assertThat(hatVerloren, Is.is(false));
		spiel.naechsterSpieler();

		assertThat(hatVerloren, Is.is(true));
	}

	boolean hatGewonnen = false;

	@Test
	public void spielerGewinntWennAlleAnderenVerlorenHaben()
	{
		List<Feld> felder = new ArrayList<>();
		felder.add(StrasseTest.getDefaultStrasse());
		Spiel spiel = new SpielImpl(felder, new EinstellungenImpl(), new WuerfelImpl());
		spiel.fuegeSpielerHinzu(getSpieler(false));
		spiel.fuegeSpielerHinzu(getSpieler(true));

		spiel.naechsterSpieler();

		assertThat(hatGewonnen, Is.is(true));
	}

	private Spieler getSpieler(boolean gewinntImmer)
	{
		return new Spieler()
		{

			@Override
			public void zeigeTransaktionsvorschlag(Transaktion transaktion)
			{}

			@Override
			public void verloren()
			{
				hatGewonnen = true;
			}

			@Override
			public void gewonnen()
			{
				hatVerloren = true;
			}

			@Override
			public void zeigeKaufmoeglichkeit(Strasse strasse)
			{}

			@Override
			public void zeigeKarte(Karte karte)
			{}

			@Override
			public void ueberweise(RessourcenDatensatz datensatz, Spieler empfaenger)
			{}

			@Override
			public void setFeldNr(int feldNrSoll)
			{}

			@Override
			public void setAkutellerSpieler(boolean isAktuell)
			{}

			@Override
			public boolean kannBezahlen(List<RessourcenDatensatz> kostenHaus)
			{
				return false;
			}

			@Override
			public boolean isNegative()
			{
				return !gewinntImmer;
			}

			@Override
			public boolean isAktuellerSpieler()
			{
				return false;
			}

			@Override
			public int getSpielerNr()
			{
				return 0;
			}

			@Override
			public int getRessourcenWerte(Ressource ressource)
			{
				return 0;
			}

			@Override
			public List<RessourcenDatensatz> getRessourcenTransaktionen()
			{
				return null;
			}

			@Override
			public String getName()
			{
				return null;
			}

			@Override
			public int getFeldNr()
			{
				return 0;
			}

			@Override
			public void einzahlen(List<RessourcenDatensatz> datensaetze)
			{}

			@Override
			public void einzahlen(RessourcenDatensatz datensatz)
			{}

			@Override
			public void auszahlen(List<RessourcenDatensatz> datensaetze)
			{}

			@Override
			public void auszahlen(RessourcenDatensatz datensatz)
			{}

			@Override
			public void addBeobachterHinzu(Beobachter beobachter)
			{}

			@Override
			public List<Feld> getFelder()
			{
				return null;
			}

			@Override
			public void feldHinzu(Feld feld)
			{}

			@Override
			public void feldWeg(Feld feld)
			{}

			@Override
			public void setSpielerNr(int nr)
			{}

			@Override
			public boolean hatVerloren()
			{
				return false;
			}
		};
	}
}
