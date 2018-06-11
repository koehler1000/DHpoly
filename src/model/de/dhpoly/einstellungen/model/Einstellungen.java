package de.dhpoly.einstellungen.model;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.einstellungen.view.EinstellungenUI;
import de.dhpoly.karte.model.BezahlKarte;
import de.dhpoly.karte.model.Karte;
import de.dhpoly.karte.model.RueckenKarte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.karte.model.WetterKarte;
import de.dhpoly.kartenstapel.model.BezahlZiel;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;

public class Einstellungen extends Datenobjekt {
	private static final long serialVersionUID = 1L;

	private int startguthaben = 1000;

	private int betragBetretenLos = 100;
	private int betragPassierenLos = 100;

	// Kosten je auf Seite 1 -> Seite 2 = Kosten von Seite 1 * 2
	private int kostenHausStein = 10;
	private int kostenHausGeld = 100;
	private int kostenHausHolz = 10;

	private int ressourcenErtrag = 10;

	private List<Karte> karten = new ArrayList<>();

	public Einstellungen() {
		RessourcenDatensatz geld = new RessourcenDatensatz(Ressource.GELD, 10, "Ereigniskarte");
		karten.add(new BezahlKarte("Zahle 10� an alle", BezahlZiel.SPIELER_ZIEHER, BezahlZiel.SPIELER_ANDERE, geld));
		karten.add(new BezahlKarte("Erhalte 10� von der Bank", BezahlZiel.BANK, BezahlZiel.SPIELER_ZIEHER, geld));
		karten.add(new BezahlKarte("Sammle Spenden", BezahlZiel.SPIELER_ANDERE, BezahlZiel.SPIELER_ZIEHER, geld));
		karten.add(new BezahlKarte("Erdbeben, alle Spieler verlieren 10�", BezahlZiel.ALLE_SPIELER, BezahlZiel.BANK, geld));
		karten.add(new WetterKarte(Wetter.SONNE));
		karten.add(new WetterKarte(Wetter.BEWOELKT));
		karten.add(new WetterKarte(Wetter.GEWITTER));
		karten.add(new WetterKarte(Wetter.SCHNEE));
		karten.add(new WetterKarte(Wetter.REGEN));
		karten.add(new RueckenKarte(3));
		karten.add(new RueckenKarte(-3));
		karten.add(new RueckenKarte(5));
		karten.add(new RueckenKarte(-5));
	}

	public int getBetragBetretenLos() {
		return betragBetretenLos;
	}

	public void setBetragBetretenLos(int betragBetretenLos) {
		this.betragBetretenLos = betragBetretenLos;
	}

	public int getKostenHausStein() {
		return kostenHausStein;
	}

	public void setKostenHausStein(int kostenHausStein) {
		this.kostenHausStein = kostenHausStein;
	}

	public int getBetragPassierenLos() {
		return betragPassierenLos;
	}

	public void setBetragPassierenLos(int betragPassierenLos) {
		this.betragPassierenLos = betragPassierenLos;
	}

	public int getStartguthaben() {
		return startguthaben;
	}

	public void setStartguthaben(int startguthaben) {
		this.startguthaben = startguthaben;
	}

	public int getRessourcenErtrag() {
		return ressourcenErtrag;
	}

	public void setRessourcenErtrag(int ressourcenErtrag) {
		this.ressourcenErtrag = ressourcenErtrag;
	}

	public int getKostenHausGeld() {
		return kostenHausGeld;
	}

	public void setKostenHausGeld(int kostenHausGeld) {
		this.kostenHausGeld = kostenHausGeld;
	}

	public int getKostenHausHolz() {
		return kostenHausHolz;
	}

	public void setKostenHausHolz(int kostenHausHolz) {
		this.kostenHausHolz = kostenHausHolz;
	}

	public List<RessourcenDatensatz> getHauskosten(int seite) {
		List<RessourcenDatensatz> ressourcenDatensaetze = new ArrayList<>();
		ressourcenDatensaetze.add(new RessourcenDatensatz(Ressource.GELD, kostenHausGeld * seite, "Hauskosten"));
		ressourcenDatensaetze.add(new RessourcenDatensatz(Ressource.HOLZ, kostenHausHolz * seite, "Hausbau"));
		ressourcenDatensaetze.add(new RessourcenDatensatz(Ressource.STEIN, kostenHausStein * seite, "Hausbau"));
		return ressourcenDatensaetze;
	}

	public List<RessourcenDatensatz> getSpielerStartVorraete() {
		List<RessourcenDatensatz> ressourcenDatensaetze = new ArrayList<>();
		ressourcenDatensaetze.add(new RessourcenDatensatz(Ressource.GELD, startguthaben, "Startguthaben"));
		return ressourcenDatensaetze;
	}

	@Override
	public String getTitel() {
		return "Einstellungen";
	}

	@Override
	public Class<? extends Oberflaeche> getClassUI() {
		return EinstellungenUI.class;
	}

	public List<Karte> getEreigniskarten() {
		return karten;
	}
}
