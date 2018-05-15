package de.dhpoly.spiel.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.fakes.ClientFake;
import de.dhpoly.fakes.ServerFake;
import de.dhpoly.feld.model.FeldDaten;
import de.dhpoly.feld.model.LosfeldDaten;
import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.netzwerk.Client;
import de.dhpoly.netzwerk.Datenobjektverwalter;
import de.dhpoly.netzwerk.Server;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.model.SpielfeldDaten;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.model.SpielerStatus;
import de.dhpoly.spieler.model.SpielerTyp;
import de.dhpoly.wuerfel.model.WuerfelDaten;

public class SpielImplTest implements Datenobjektverwalter
{
	private SpielImpl spiel;
	private List<Datenobjekt> empfangeneObjekte = new ArrayList<>();
	private Server server;
	private Client client;

	@Before
	public void vorbereitung()
	{
		server = ServerFake.serverfake;
		client = ClientFake.clientFake;

		client.setDatenobjektverwalter(this);

		List<FeldDaten> felder = new ArrayList<>();
		felder.add(new LosfeldDaten());
		felder.add(new StrasseDaten());
		spiel = new SpielImpl(server);
		spiel.setFelder(felder);
		spiel.fuegeSpielerHinzu(new Spieler(SpielerTyp.COMPUTER, "Test1"));
		spiel.fuegeSpielerHinzu(new Spieler(SpielerTyp.COMPUTER, "Test2"));
	}

	@Test
	public void spielStartVersendetSpielfeld()
	{
		spiel.starteSpiel();
		assertThat(isEmpfangen(SpielfeldDaten.class), Is.is(1L));
	}

	@Test
	public void spielStartSendetDatenobjektAnSpieler()
	{
		spiel.starteSpiel();
		assertThat(isEmpfangen(Spieler.class), Is.is(2L));
	}

	@Test
	public void spielerWechselSendetDatenobjektAnSpieler()
	{
		spiel.starteSpiel();
		empfangeneObjekte = new ArrayList<>();
		spiel.wuerfelWeitergeben(spiel.getAktuellerSpieler());

		assertThat(isEmpfangen(Spieler.class), Is.is(2L));
	}

	@Test
	public void wuerfelnSendetDatenobjekt()
	{
		spiel.starteSpiel();
		empfangeneObjekte = new ArrayList<>();

		spiel.wuerfeln(spiel.getAktuellerSpieler());

		assertThat(isEmpfangen(WuerfelDaten.class), Is.is(1L));
	}

	@Test
	public void testaktuellerSpieler()
	{
		assertEquals("Test1", spiel.getAktuellerSpieler().getName());
	}

	@Test
	public void testnaechsterSpieler()
	{
		spiel.wuerfelWeitergeben(spiel.getAktuellerSpieler());
		assertEquals("Test2", spiel.getAktuellerSpieler().getName());
	}

	@Test
	public void geldBeiUeberLos() throws InterruptedException
	{
		int geldVorDemLaufen = spiel.getAktuellerSpieler().getRessourcenWert(Ressource.GELD);

		spiel.ruecke(spiel.getAktuellerSpieler(), 2);

		assertThat(spiel.getAktuellerSpieler().getRessourcenWert(Ressource.GELD),
				Is.is(geldVorDemLaufen + new Einstellungen().getBetragPassierenLos()));
	}

	boolean hatVerloren = false;

	@Test
	public void spielerVerliertWennErAmEndeDesZugesKeinGeldMehrHat()
	{
		List<FeldDaten> felder = new ArrayList<>();
		felder.add(new StrasseDaten());
		Spiel spiel = SpielImplTest.getDefaultSpiel();
		spiel.setFelder(felder);

		Spieler s1 = new Spieler(SpielerTyp.LOKAL, "Peter");
		Spieler s2 = new Spieler(SpielerTyp.LOKAL, "Peter");

		spiel.fuegeSpielerHinzu(s1);
		spiel.fuegeSpielerHinzu(s2);

		s1.auszahlen(new RessourcenDatensatz(Ressource.GELD, 9999999));

		assertThat(s1.getStatus(), Is.is(SpielerStatus.IM_SPIEL));
		spiel.wuerfelWeitergeben(s1);

		assertThat(s1.getStatus(), Is.is(SpielerStatus.VERLOREN));
		assertThat(s2.getStatus(), Is.is(SpielerStatus.GEWONNEN));
	}

	public static SpielImpl getDefaultSpiel()
	{
		return getDefaultSpiel(new Einstellungen());
	}

	public static SpielImpl getDefaultSpiel(Einstellungen einstellungen)
	{
		List<FeldDaten> felder = new ArrayList<>();
		felder.add(new LosfeldDaten());

		SpielImpl spiel = new SpielImpl();
		spiel.setFelder(felder);

		return spiel;
	}

	@Override
	public void empfange(Datenobjekt datenobjekt)
	{
		empfangeneObjekte.add(datenobjekt);
	}

	public long isEmpfangen(Class<? extends Datenobjekt> c)
	{
		return empfangeneObjekte.stream().filter(e -> (c.isInstance(e))).count();
	}
}
