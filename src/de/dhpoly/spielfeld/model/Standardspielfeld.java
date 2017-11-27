package de.dhpoly.spielfeld.model;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.einstellungen.model.EinstellungenImpl;
import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.feld.control.Ereignisfeld;
import de.dhpoly.feld.control.FelderverwaltungImpl;
import de.dhpoly.feld.control.Losfeld;
import de.dhpoly.feld.control.Ressourcenfeld;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.karte.Karte;
import de.dhpoly.karte.control.BezahlKarte;
import de.dhpoly.kartenstapel.Kartenstapel;
import de.dhpoly.kartenstapel.control.KartenstapelImpl;
import de.dhpoly.kartenstapel.model.GeldTransfer;
import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.control.RessourcenDatensatzImpl;
import de.dhpoly.ressource.model.Ressource;

public class Standardspielfeld
{
	public List<Feld> getStandardSpielfeld()
	{
		List<Karte> karten = new ArrayList<>();
		List<Karte> superevent = new ArrayList<>();

		Kartenstapel kartenstapel = new KartenstapelImpl(karten);
		Kartenstapel kartenstapelSuperevents = new KartenstapelImpl(superevent);

		karten.add(new BezahlKarte("bla", GeldTransfer.ANDERESPIELER_SPIELER,
				new RessourcenDatensatzImpl(Ressource.GELD, 100)));

		superevent.add(new BezahlKarte("foo", GeldTransfer.ANDERESPIELER_SPIELER,
				new RessourcenDatensatzImpl(Ressource.GELD, 150)));

		Einstellungen einstellungen = new EinstellungenImpl();
		Felderverwaltung verwaltung = new FelderverwaltungImpl();

		int seite = 1;
		List<Feld> standardfeld = new ArrayList<>();

		standardfeld.add(new Losfeld(einstellungen));
		standardfeld.add(
				new Strasse(verwaltung, 100, new int[] { 10, 11, 13, 15, 20, 30 }, getHauskosten(seite), 1, "Ort2"));
		standardfeld.add(new Ereignisfeld(kartenstapel));
		standardfeld.add(new Ressourcenfeld(Ressource.STEIN));
		standardfeld.add(
				new Strasse(verwaltung, 110, new int[] { 11, 12, 14, 16, 22, 33 }, getHauskosten(seite), 1, "Ort2"));
		standardfeld.add(new Ressourcenfeld(Ressource.HOLZ));
		standardfeld.add(
				new Strasse(verwaltung, 150, new int[] { 15, 16, 19, 24, 30, 45 }, getHauskosten(seite), 2, "Ort3"));
		standardfeld.add(new Ereignisfeld(kartenstapel));
		standardfeld.add(
				new Strasse(verwaltung, 160, new int[] { 16, 17, 20, 25, 32, 48 }, getHauskosten(seite), 2, "Ort4"));
		standardfeld.add(
				new Strasse(verwaltung, 170, new int[] { 17, 18, 21, 25, 34, 51 }, getHauskosten(seite), 2, "Ort5"));

		seite = 2;
		standardfeld.add(new Ereignisfeld(kartenstapelSuperevents));
		standardfeld.add(
				new Strasse(verwaltung, 200, new int[] { 20, 22, 25, 30, 40, 60 }, getHauskosten(seite), 3, "Ort6"));
		standardfeld.add(
				new Strasse(verwaltung, 210, new int[] { 21, 22, 26, 31, 42, 63 }, getHauskosten(seite), 3, "Ort7"));
		standardfeld.add(new Ereignisfeld(kartenstapel));
		standardfeld.add(
				new Strasse(verwaltung, 220, new int[] { 22, 24, 28, 33, 44, 66 }, getHauskosten(seite), 3, "Ort8"));
		standardfeld.add(new Ressourcenfeld(Ressource.HOLZ));
		standardfeld.add(new Strasse(verwaltung, 250, new int[] { 25, 27, 31, 37, 50, 75 }, getHauskosten(seite), 4,
				"Düsseldorf"));
		standardfeld.add(new Strasse(verwaltung, 260, new int[] { 26, 28, 32, 39, 52, 78 }, getHauskosten(seite), 4,
				"Dortmund"));
		standardfeld.add(new Ereignisfeld(kartenstapel));

		seite = 3;
		standardfeld.add(
				new Strasse(verwaltung, 270, new int[] { 27, 29, 34, 41, 54, 81 }, getHauskosten(seite), 4, "Erfurt"));
		standardfeld.add(new Ereignisfeld(kartenstapelSuperevents));
		standardfeld.add(
				new Strasse(verwaltung, 300, new int[] { 30, 33, 38, 45, 60, 90 }, getHauskosten(seite), 5, "Köln"));
		standardfeld.add(new Ressourcenfeld(Ressource.STEIN));
		standardfeld.add(new Strasse(verwaltung, 310, new int[] { 31, 34, 39, 46, 62, 93 }, getHauskosten(seite), 5,
				"Frankfurt am Main"));
		standardfeld.add(
				new Strasse(verwaltung, 320, new int[] { 32, 35, 40, 48, 64, 96 }, getHauskosten(seite), 5, "Kiel"));
		standardfeld.add(new Ressourcenfeld(Ressource.HOLZ));
		standardfeld.add(new Strasse(verwaltung, 350, new int[] { 35, 39, 44, 53, 70, 105 }, getHauskosten(seite), 6,
				"Mannheim"));
		standardfeld.add(new Ereignisfeld(kartenstapel));
		standardfeld.add(new Strasse(verwaltung, 360, new int[] { 36, 40, 45, 54, 72, 108 }, getHauskosten(seite), 6,
				"Potsdam"));

		seite = 4;
		standardfeld.add(new Strasse(verwaltung, 370, new int[] { 37, 41, 47, 56, 74, 111 }, getHauskosten(seite), 6,
				"Stuttgart"));
		standardfeld.add(new Ereignisfeld(kartenstapelSuperevents));
		standardfeld.add(new Strasse(verwaltung, 400, new int[] { 40, 44, 50, 60, 80, 120 }, getHauskosten(seite), 7,
				"Saarbrücken"));
		standardfeld.add(new Ereignisfeld(kartenstapel));
		standardfeld.add(new Strasse(verwaltung, 410, new int[] { 41, 45, 51, 61, 82, 123 }, getHauskosten(seite), 7,
				"Karlsruhe"));
		standardfeld.add(new Strasse(verwaltung, 420, new int[] { 42, 46, 53, 63, 84, 126 }, getHauskosten(seite), 7,
				"Dresden"));
		standardfeld.add(new Ressourcenfeld(Ressource.HOLZ));
		standardfeld.add(new Strasse(verwaltung, 500, new int[] { 50, 55, 61, 76, 100, 150 }, getHauskosten(seite), 8,
				"Berlin"));
		standardfeld.add(new Ereignisfeld(kartenstapel));
		standardfeld.add(new Strasse(verwaltung, 510, new int[] { 51, 56, 62, 77, 102, 153 }, getHauskosten(seite), 8,
				"Hamburg"));
		standardfeld.add(new Strasse(verwaltung, 520, new int[] { 52, 57, 63, 78, 104, 156 }, getHauskosten(seite), 8,
				"München"));

		verwaltung.setFelder(standardfeld);
		return standardfeld;
	}

	private List<RessourcenDatensatz> getHauskosten(int seite)
	{
		List<RessourcenDatensatz> ressourcenDatensaetze = new ArrayList<>();
		ressourcenDatensaetze.add(new RessourcenDatensatzImpl(Ressource.GELD, 100 * seite));
		ressourcenDatensaetze.add(new RessourcenDatensatzImpl(Ressource.HOLZ, 100 * seite));
		ressourcenDatensaetze.add(new RessourcenDatensatzImpl(Ressource.STEIN, 100 * seite));
		return ressourcenDatensaetze;
	}
}
