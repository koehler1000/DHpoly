package de.dhpoly.handel.control;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.fehler.model.Fehler;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.feld.control.FeldStrasse;
import de.dhpoly.feld.control.FeldStrasseTest;
import de.dhpoly.feld.control.FelderverwaltungTest;
import de.dhpoly.feld.model.StrasseKaufen;
import de.dhpoly.handel.Handel;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.karte.model.Karte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.model.SpielStatus;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImplTest;
import de.dhpoly.spieler.model.SpielerDaten;
import de.dhpoly.wuerfel.Wuerfelpaar;

public class HandelImplTest
{
	Spieler s1 = SpielerImplTest.getDefaultSpieler(150);
	Spieler s2 = SpielerImplTest.getDefaultSpieler(250);

	@Test
	public void felderVerbuchenStrassenGeben()
	{
		Felderverwaltung verwaltung = FelderverwaltungTest.getDefaultFelderverwaltung();

		s1 = SpielerImplTest.getDefaultSpieler(spiel);
		s2 = SpielerImplTest.getDefaultSpieler(spiel);

		spiel.fuegeSpielerHinzu(s1);
		spiel.fuegeSpielerHinzu(s2);

		List<Feld> felder = new ArrayList<>();
		FeldStrasse feld = FeldStrasseTest.getDefaultStrasse(verwaltung, s1);
		felder.add(feld);

		feld.kaufe(s1, 0);
		assertThat(feld.getEigentuemer().get(), Is.is(s1.getDaten()));

		Handel handel = new HandelImpl();

		Transaktion transaktion = new Transaktion(s1.getDaten(), s2.getDaten());
		transaktion.addDatensatzFelderwechsel(feld);

		handel.vorschlagAnnehmen(transaktion, spiel);

		assertTrue(feld.getEigentuemer().isPresent());
		assertThat(feld.getEigentuemer().get(), Is.is(s2.getDaten()));
	}

	@Test
	public void felderVerbuchenStrassenBekommen()
	{
		Felderverwaltung verwaltung = FelderverwaltungTest.getDefaultFelderverwaltung();

		s1 = SpielerImplTest.getDefaultSpieler();
		s2 = SpielerImplTest.getDefaultSpieler();

		spiel.fuegeSpielerHinzu(s1);
		spiel.fuegeSpielerHinzu(s2);

		FeldStrasse feld = FeldStrasseTest.getDefaultStrasse(verwaltung, s2);

		List<Feld> felder = new ArrayList<>();
		felder.add(feld);

		List<RessourcenDatensatz> datensaetze = new ArrayList<>();
		RessourcenDatensatz datensatz = new RessourcenDatensatz(Ressource.GELD, 50);
		datensaetze.add(datensatz);

		Handel handel = new HandelImpl();

		Transaktion transaktion = new Transaktion(s1.getDaten(), s2.getDaten());
		transaktion.addDatensatzFelderwechsel(feld);
		handel.vorschlagAnnehmen(transaktion, spiel);

		assertTrue(feld.getEigentuemer().isPresent());
		assertThat(feld.getEigentuemer().get(), Is.is(s1.getDaten()));
	}

	@Test
	public void felderVerbuchenGeldtransfer()
	{
		Handel handel = new HandelImpl();

		spiel.fuegeSpielerHinzu(s1);
		spiel.fuegeSpielerHinzu(s2);

		Transaktion transaktion = new Transaktion(s1.getDaten(), s2.getDaten());
		transaktion.setRessourcen(s2.getDaten(), Ressource.GELD, 50);
		handel.vorschlagAnnehmen(transaktion, spiel);

		assertThat(s1.getRessourcenWerte(Ressource.GELD), Is.is(200));
		assertThat(s2.getRessourcenWerte(Ressource.GELD), Is.is(200));
	}

	Spiel spiel = new Spiel()
	{
		@Override
		public void wuerfeln(Spieler spieler)
		{}

		@Override
		public void wuerfelWeitergeben(Spieler spieler)
		{}

		@Override
		public void verarbeiteKarte(Karte karte)
		{}

		@Override
		public void verarbeiteFehler(Fehler fehler)
		{}

		@Override
		public void starteSpiel()
		{}

		@Override
		public void setWuerfelPaar(Wuerfelpaar wuerfelPaar)
		{}

		@Override
		public void setWetter(Wetter wetter)
		{}

		@Override
		public void setFelder(List<Feld> felder)
		{}

		@Override
		public void setEinstellungen(Einstellungen einstellungen)
		{}

		@Override
		public void ruecke()
		{}

		@Override
		public void kaufe(StrasseKaufen strasse, Spieler spieler)
		{}

		@Override
		public boolean kannWuerfeln(Spieler spieler)
		{
			return false;
		}

		@Override
		public boolean kannWuerfelWeitergeben(Spieler spieler)
		{
			return false;
		}

		@Override
		public Wetter getWetter()
		{
			return null;
		}

		@Override
		public SpielStatus getStatus()
		{
			return null;
		}

		@Override
		public Optional<Spieler> getSpieler(SpielerDaten spielerDaten)
		{
			if (spielerDaten == s1.getDaten())
			{
				return Optional.of(s1);
			}
			return Optional.of(s2);
		}

		@Override
		public List<Spieler> getSpieler()
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Feld> getFelder(Spieler spieler)
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Feld> getFelder()
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public double getFaktorMiete()
		{
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public SpielerDaten getAktuellerSpielerDaten()
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Spieler getAktuellerSpieler()
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void fuegeSpielerHinzu(Spieler spieler)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void fuegeLokalenSpielerHinzu(String spielerName)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void fuegeComputerSpielerHinzu(String text)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void empfange(Datenobjekt objekt)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public Optional<Spieler> getSpieler(Optional<SpielerDaten> eigentuemer)
		{
			// TODO Auto-generated method stub
			return null;
		}
	};
}
