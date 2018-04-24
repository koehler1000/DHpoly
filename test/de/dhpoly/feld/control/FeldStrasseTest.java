package de.dhpoly.feld.control;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Test;

import de.dhpoly.einstellungen.model.EinstellungenImpl;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImplTest;

public class FeldStrasseTest
{
	@Test
	public void spielerKauftStrasse()
	{
		final int startbetrag = 500;
		final int kosten = 50;

		FeldStrasse strasse = new FeldStrasse(new FelderverwaltungImpl(), kosten, new int[] { 10, 20, 30, 50, 70, 90 },
				new EinstellungenImpl().getHauskosten(1), 3, "Badstrasse");
		Spieler spieler = SpielerImplTest.getDefaultSpieler(startbetrag);

		assertThat(strasse.isKaufbar(), Is.is(true));

		strasse.kaufe(spieler);

		assertThat(strasse.isKaufbar(), Is.is(false));
		assertThat(strasse.getEigentuemer().get(), Is.is(spieler));
		assertThat(spieler.getRessourcenWerte(Ressource.GELD), Is.is(startbetrag - kosten));
	}

	@Test
	public void spielerGeldAendertSichNichtWennErAufDieEigeneStrasseKommt()
	{
		final int startbetrag = 500;
		final int kosten = 50;

		FeldStrasse strasse = new FeldStrasse(new FelderverwaltungImpl(), kosten, new int[] { 10, 20, 30, 50, 70, 90 },
				new EinstellungenImpl().getHauskosten(1), 3, "Badstrasse");
		Spieler spieler = SpielerImplTest.getDefaultSpieler(startbetrag);
		strasse.kaufe(spieler);

		assertThat(spieler.getRessourcenWerte(Ressource.GELD), Is.is(startbetrag - kosten));
		strasse.spielerBetrittFeld(spieler, Wetter.BEWOELKT); // eigentümer
		assertThat(spieler.getRessourcenWerte(Ressource.GELD), Is.is(startbetrag - kosten));
	}

	@Test
	public void hausBauenKostetRessourcen()
	{
		final int kostenHausGeld = 100;
		final int kostenHausHolz = 100;
		final int kostenHausStein = 100;

		final List<RessourcenDatensatz> kostenHaus = new ArrayList<>();
		kostenHaus.add(new RessourcenDatensatz(Ressource.GELD, kostenHausGeld));
		kostenHaus.add(new RessourcenDatensatz(Ressource.HOLZ, kostenHausHolz));
		kostenHaus.add(new RessourcenDatensatz(Ressource.STEIN, kostenHausStein));

		Spieler spieler = SpielerImplTest.getDefaultSpieler(0);
		spieler.einzahlen(kostenHaus); // spieler erhält genau das, was er für die Straße braucht

		FeldStrasse strasse = FeldStrasseTest.getDefaultStrasse(kostenHaus);
		strasse.kaufe(spieler);

		strasse.hausBauen();

		assertThat(spieler.getRessourcenWerte(Ressource.GELD), Is.is(0));
		assertThat(spieler.getRessourcenWerte(Ressource.HOLZ), Is.is(0));
		assertThat(spieler.getRessourcenWerte(Ressource.STEIN), Is.is(0));
	}

	@Test
	public void hausAnzahlErhoehtSichBeimBauen()
	{
		final int kostenHausGeld = 100;
		final int kostenHausHolz = 100;
		final int kostenHausStein = 100;

		final List<RessourcenDatensatz> kostenHaus = new ArrayList<>();
		kostenHaus.add(new RessourcenDatensatz(Ressource.GELD, kostenHausGeld));
		kostenHaus.add(new RessourcenDatensatz(Ressource.HOLZ, kostenHausHolz));
		kostenHaus.add(new RessourcenDatensatz(Ressource.STEIN, kostenHausStein));

		Spieler spieler = SpielerImplTest.getDefaultSpieler(0);
		spieler.einzahlen(kostenHaus); // spieler erhält genau das, was er für die Straße braucht

		FeldStrasse strasse = FeldStrasseTest.getDefaultStrasse(kostenHaus);
		strasse.kaufe(spieler);
		strasse.hausBauen();

		assertThat(strasse.getHaeuser(), Is.is(1));
	}

	@Test
	public void hausAbreisenBeeinflusstKontoNicht()
	{
		final int kostenHausGeld = 100;
		final int kostenHausHolz = 100;
		final int kostenHausStein = 100;

		final List<RessourcenDatensatz> kostenHaus = new ArrayList<>();
		kostenHaus.add(new RessourcenDatensatz(Ressource.GELD, kostenHausGeld));
		kostenHaus.add(new RessourcenDatensatz(Ressource.HOLZ, kostenHausHolz));
		kostenHaus.add(new RessourcenDatensatz(Ressource.STEIN, kostenHausStein));

		Spieler spieler = SpielerImplTest.getDefaultSpieler(0);
		spieler.einzahlen(kostenHaus); // spieler erhält genau das, was er für die Straße braucht

		FeldStrasse strasse = FeldStrasseTest.getDefaultStrasse(kostenHaus);
		strasse.kaufe(spieler);

		strasse.hausBauen();
		strasse.hausZerstoeren();

		assertThat(spieler.getRessourcenWerte(Ressource.GELD), Is.is(0));
		assertThat(spieler.getRessourcenWerte(Ressource.HOLZ), Is.is(0));
		assertThat(spieler.getRessourcenWerte(Ressource.STEIN), Is.is(0));
	}

	@Test
	public void hausVerkaufenBringtRessourcenZurueck()
	{
		final int kostenHausGeld = 100;
		final int kostenHausHolz = 100;
		final int kostenHausStein = 100;

		final List<RessourcenDatensatz> kostenHaus = new ArrayList<>();
		kostenHaus.add(new RessourcenDatensatz(Ressource.GELD, kostenHausGeld));
		kostenHaus.add(new RessourcenDatensatz(Ressource.HOLZ, kostenHausHolz));
		kostenHaus.add(new RessourcenDatensatz(Ressource.STEIN, kostenHausStein));

		Spieler spieler = SpielerImplTest.getDefaultSpieler(0);
		spieler.einzahlen(kostenHaus); // spieler erhält genau das, was er für die Straße braucht

		FeldStrasse strasse = FeldStrasseTest.getDefaultStrasse(kostenHaus);
		strasse.kaufe(spieler);

		strasse.hausBauen();
		strasse.hausVerkaufen();

		assertThat(spieler.getRessourcenWerte(Ressource.GELD), Is.is(0));
		assertThat(spieler.getRessourcenWerte(Ressource.HOLZ), Is.is(100));
		assertThat(spieler.getRessourcenWerte(Ressource.STEIN), Is.is(100));
	}

	private static FeldStrasse getDefaultStrasse(List<RessourcenDatensatz> kostenHaus)
	{
		return new FeldStrasse(null, 0, new int[] { 1, 2, 3 }, kostenHaus, 1, "test");
	}

	public static FeldStrasse getDefaultStrasse()
	{
		return getDefaultStrasse("test");
	}

	public static FeldStrasse getDefaultStrasse(String name)
	{
		return getDefaultStrasse(name, 0);
	}

	public static FeldStrasse getDefaultStrasse(String name, int preis)
	{
		return getDefaultStrasse(name, preis, new int[1]);
	}

	public static FeldStrasse getDefaultStrasse(String name, int preis, int[] mieten)
	{
		return new FeldStrasse(null, preis, mieten, new EinstellungenImpl().getHauskosten(1), 1, name);
	}

	public static FeldStrasse getDefaultStrasse(Felderverwaltung verwaltung, Spieler s1)
	{
		FeldStrasse strasse = new FeldStrasse(verwaltung, 0, new int[1], new EinstellungenImpl().getHauskosten(1), 1,
				"Strasse");
		strasse.setEigentuemer(s1);
		return strasse;
	}

	public static FeldStrasse getDefaultStrasse(int[] miete)
	{
		return getDefaultStrasse("Teststrasse", 1000, miete);
	}

	public static FeldStrasse getDefaultStrasse(int[] miete, Felderverwaltung verwaltung)
	{
		return new FeldStrasse(verwaltung, 1000, miete, new EinstellungenImpl().getHauskosten(1), 1, "Teststrasse");
	}
}
