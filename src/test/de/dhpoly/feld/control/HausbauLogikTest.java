package de.dhpoly.feld.control;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import de.dhpoly.feld.model.Hausbau;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielImplTest;
import de.dhpoly.spieler.control.SpielerImplTest;
import de.dhpoly.spieler.model.Spieler;

public class HausbauLogikTest
{
	private final int kostenHausGeld = 100;
	private final int kostenHausHolz = 100;
	private final int kostenHausStein = 100;
	private final List<RessourcenDatensatz> kostenHaus = new ArrayList<>();
	private Spieler spieler = SpielerImplTest.getDefaultSpieler(0);
	private FeldStrasse strasse;
	private Spiel spiel = SpielImplTest.getDefaultSpiel();
	private HausbauLogik hausbauer = new HausbauLogik();

	@Before
	public void startUp()
	{
		strasse = new FeldStrasse(0, new int[] { 1, 2, 3 }, kostenHaus, 1, "test");

		kostenHaus.add(new RessourcenDatensatz(Ressource.GELD, kostenHausGeld));
		kostenHaus.add(new RessourcenDatensatz(Ressource.HOLZ, kostenHausHolz));
		kostenHaus.add(new RessourcenDatensatz(Ressource.STEIN, kostenHausStein));

		spiel.fuegeSpielerHinzu(spieler);
	}

	@Test
	public void hausBauenKostetRessourcen()
	{
		spieler.einzahlen(kostenHaus); // spieler erhält genau das, was er für die Straße braucht
		strasse.kaufe(spieler);

		hausbauer.verarbeite(new Hausbau(strasse.getStrasse(), 1), spiel);

		assertThat(spieler.getRessourcenWert(Ressource.GELD), Is.is(0));
		assertThat(spieler.getRessourcenWert(Ressource.HOLZ), Is.is(0));
		assertThat(spieler.getRessourcenWert(Ressource.STEIN), Is.is(0));
	}

	@Test
	public void hausVerkaufenBringtRessourcenZurueck()
	{
		spieler.einzahlen(kostenHaus);
		strasse.kaufe(spieler);

		hausbauer.verarbeite(new Hausbau(strasse.getStrasse(), 1), spiel);
		hausbauer.verarbeite(new Hausbau(strasse.getStrasse(), -1), spiel);

		assertThat(spieler.getRessourcenWert(Ressource.GELD), Is.is(0));
		assertThat(spieler.getRessourcenWert(Ressource.HOLZ), Is.is(100));
		assertThat(spieler.getRessourcenWert(Ressource.STEIN), Is.is(100));
	}

	@Test
	public void hausAnzahlErhoehtSichBeimBauen()
	{
		spieler.einzahlen(kostenHaus); // spieler erhält genau das, was er für die Straße braucht
		strasse.kaufe(spieler);

		hausbauer.verarbeite(new Hausbau(strasse.getStrasse(), 1), spiel);

		assertThat(strasse.getHaeuser(), Is.is(1));
	}

	@Test
	public void hausAnzahlVermindertSichBeimAbriss()
	{
		spieler.einzahlen(kostenHaus); // spieler erhält genau das, was er für die Straße braucht
		strasse.kaufe(spieler);

		hausbauer.verarbeite(new Hausbau(strasse.getStrasse(), 1), spiel);
		hausbauer.verarbeite(new Hausbau(strasse.getStrasse(), -1), spiel);

		assertThat(strasse.getHaeuser(), Is.is(0));
	}

	@Test
	public void hausAnzahlNichtNegativ()
	{
		spieler.einzahlen(kostenHaus); // spieler erhält genau das, was er für die Straße braucht
		strasse.kaufe(spieler);

		hausbauer.verarbeite(new Hausbau(strasse.getStrasse(), -1), spiel);

		assertThat(strasse.getHaeuser(), Is.is(0));
	}
}
