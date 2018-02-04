package de.dhpoly.spiel;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.feld.Feld;
import de.dhpoly.karte.Karte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.wuerfel.Wuerfel;
import observerpattern.Beobachter;

public interface Spiel
{
	static JTabbedPane pnlInhalt = new JTabbedPane();

	public static void setPanel(String beschreibung, JPanel pnl)
	{
		pnlInhalt.removeAll();
		pnlInhalt.addTab(beschreibung, pnl);
	}

	public static void leerePanel()
	{
		pnlInhalt.removeAll();
	}

	Spieler getAktuellerSpieler();

	Einstellungen getEinstellungen();

	Wetter getWetter();

	Wuerfel getWuerfel();

	List<Spieler> getSpieler();

	List<Feld> getFelder();

	double getFaktorMiete();

	void naechsterSpieler();

	void ruecke();

	void verarbeiteKarte(Karte karte);

	void fuegeSpielerHinzu(Spieler spieler);

	void naechsterSchritt();

	String getBeschreibungNaechsterSchritt();

	void addBeobachter(Beobachter beobachter);

}
