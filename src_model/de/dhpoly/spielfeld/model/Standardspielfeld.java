package de.dhpoly.spielfeld.model;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.feld.control.FeldEreignis;
import de.dhpoly.feld.control.FeldLos;
import de.dhpoly.feld.control.FeldRessource;
import de.dhpoly.feld.control.FeldStrasse;
import de.dhpoly.feld.control.FelderverwaltungImpl;
import de.dhpoly.karte.model.BezahlKarte;
import de.dhpoly.karte.model.Karte;
import de.dhpoly.kartenstapel.Kartenstapel;
import de.dhpoly.kartenstapel.control.KartenstapelImpl;
import de.dhpoly.kartenstapel.model.GeldTransfer;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;

public class Standardspielfeld
{
	public List<Feld> getStandardSpielfeld(Einstellungen einstellungen)
	{
		List<Karte> karten = new ArrayList<>();
		List<Karte> superevent = new ArrayList<>();

		Kartenstapel kartenstapel = new KartenstapelImpl(karten);
		Kartenstapel kartenstapelSuperevents = new KartenstapelImpl(superevent);

		karten.add(new BezahlKarte("Zahle Geld an deine Mitspieler", GeldTransfer.SPIELER_ANDERESPIELER,
				new RessourcenDatensatz(Ressource.GELD, 100, "Ereigniskarte")));

		superevent.add(new BezahlKarte("Du erhältst supermäßige 150 € von deinen Mitspielern",
				GeldTransfer.ANDERESPIELER_SPIELER, new RessourcenDatensatz(Ressource.GELD, 150, "Superevent")));
		superevent.add(new BezahlKarte("Ein Holz-LKW verunglückt auf deinem Gebiet. Du erhältst 100 Holz.",
				GeldTransfer.BANK_SPIELER, new RessourcenDatensatz(Ressource.HOLZ, 100, "Holz-LKW")));

		Felderverwaltung verwaltung = new FelderverwaltungImpl();

		int seite = 1;
		List<Feld> standardfeld = new ArrayList<>();

		standardfeld.add(new FeldLos(einstellungen));
		standardfeld.add(new FeldStrasse(verwaltung, 100, new int[] { 10, 11, 13, 15, 20, 30 }, getHauskosten(seite), 1,
				"Weilheim"));
		standardfeld.add(new FeldEreignis(kartenstapel));
		standardfeld.add(new FeldRessource(Ressource.STEIN, einstellungen));
		standardfeld.add(new FeldStrasse(verwaltung, 110, new int[] { 11, 12, 14, 16, 22, 33 }, getHauskosten(seite), 1,
				"Esslingen"));
		standardfeld.add(new FeldRessource(Ressource.HOLZ, einstellungen));
		standardfeld.add(
				new FeldStrasse(verwaltung, 150, new int[] { 15, 16, 19, 24, 30, 45 }, getHauskosten(seite), 2, "Ulm"));
		standardfeld.add(new FeldEreignis(kartenstapel));
		standardfeld.add(new FeldStrasse(verwaltung, 160, new int[] { 16, 17, 20, 25, 32, 48 }, getHauskosten(seite), 2,
				"Neu-Ulm"));
		standardfeld.add(new FeldStrasse(verwaltung, 170, new int[] { 17, 18, 21, 25, 34, 51 }, getHauskosten(seite), 2,
				"Bruchsal"));

		seite = 2;
		standardfeld.add(new FeldEreignis(kartenstapelSuperevents));
		standardfeld.add(new FeldStrasse(verwaltung, 200, new int[] { 20, 22, 25, 30, 40, 60 }, getHauskosten(seite), 3,
				"Bretten"));
		standardfeld.add(new FeldStrasse(verwaltung, 210, new int[] { 21, 22, 26, 31, 42, 63 }, getHauskosten(seite), 3,
				"Dresden"));
		standardfeld.add(new FeldEreignis(kartenstapel));
		standardfeld.add(new FeldStrasse(verwaltung, 220, new int[] { 22, 24, 28, 33, 44, 66 }, getHauskosten(seite), 3,
				"Bielefeld"));
		standardfeld.add(new FeldRessource(Ressource.HOLZ, einstellungen));
		standardfeld.add(new FeldStrasse(verwaltung, 250, new int[] { 25, 27, 31, 37, 50, 75 }, getHauskosten(seite), 4,
				"Aschheim"));
		standardfeld.add(new FeldStrasse(verwaltung, 260, new int[] { 26, 28, 32, 39, 52, 78 }, getHauskosten(seite), 4,
				"Dortmund"));
		standardfeld.add(new FeldEreignis(kartenstapel));

		seite = 3;
		standardfeld.add(new FeldStrasse(verwaltung, 270, new int[] { 27, 29, 34, 41, 54, 81 }, getHauskosten(seite), 4,
				"Erfurt"));
		standardfeld.add(new FeldEreignis(kartenstapelSuperevents));
		standardfeld.add(new FeldStrasse(verwaltung, 300, new int[] { 30, 33, 38, 45, 60, 90 }, getHauskosten(seite), 5,
				"Köln"));
		standardfeld.add(new FeldRessource(Ressource.STEIN, einstellungen));
		standardfeld.add(new FeldStrasse(verwaltung, 310, new int[] { 31, 34, 39, 46, 62, 93 }, getHauskosten(seite), 5,
				"Frankfurt"));
		standardfeld.add(new FeldStrasse(verwaltung, 320, new int[] { 32, 35, 40, 48, 64, 96 }, getHauskosten(seite), 5,
				"Kiel"));
		standardfeld.add(new FeldRessource(Ressource.HOLZ, einstellungen));
		standardfeld.add(new FeldStrasse(verwaltung, 350, new int[] { 35, 39, 44, 53, 70, 105 }, getHauskosten(seite),
				6, "Mannheim"));
		standardfeld.add(new FeldEreignis(kartenstapel));
		standardfeld.add(new FeldStrasse(verwaltung, 360, new int[] { 36, 40, 45, 54, 72, 108 }, getHauskosten(seite),
				6, "Potsdam"));

		seite = 4;
		standardfeld.add(new FeldStrasse(verwaltung, 370, new int[] { 37, 41, 47, 56, 74, 111 }, getHauskosten(seite),
				6, "Stuttgart"));
		standardfeld.add(new FeldEreignis(kartenstapelSuperevents));
		standardfeld.add(new FeldStrasse(verwaltung, 400, new int[] { 40, 44, 50, 60, 80, 120 }, getHauskosten(seite),
				7, "Münster"));
		standardfeld.add(new FeldEreignis(kartenstapel));
		standardfeld.add(new FeldStrasse(verwaltung, 410, new int[] { 41, 45, 51, 61, 82, 123 }, getHauskosten(seite),
				7, "Karlsruhe"));
		standardfeld.add(new FeldStrasse(verwaltung, 420, new int[] { 42, 46, 53, 63, 84, 126 }, getHauskosten(seite),
				7, "Dresden"));
		standardfeld.add(new FeldRessource(Ressource.HOLZ, einstellungen));
		standardfeld.add(new FeldStrasse(verwaltung, 500, new int[] { 50, 55, 61, 76, 100, 150 }, getHauskosten(seite),
				8, "Berlin"));
		standardfeld.add(new FeldEreignis(kartenstapel));
		standardfeld.add(new FeldStrasse(verwaltung, 510, new int[] { 51, 56, 62, 77, 102, 153 }, getHauskosten(seite),
				8, "Hamburg"));
		standardfeld.add(new FeldStrasse(verwaltung, 520, new int[] { 52, 57, 63, 78, 104, 156 }, getHauskosten(seite),
				8, "München"));

		verwaltung.setFelder(standardfeld);
		return standardfeld;
	}

	private List<RessourcenDatensatz> getHauskosten(int seite)
	{
		Einstellungen einstellungen = new Einstellungen();
		return einstellungen.getHauskosten(seite);
	}
}
