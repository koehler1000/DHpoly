package de.dhpoly.spiel;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.feld.Feld;
import de.dhpoly.karte.Karte;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.wuerfel.Wuerfel;
import observerpattern.Beobachter;

public interface Spiel
{
	static JPanel pnlInhalt = new JPanel(new GridLayout(1, 1));

	public static void setPanel(JPanel pnl)
	{
		pnlInhalt.removeAll();
		pnlInhalt.add(pnl);

		pnlInhalt.revalidate();
	}

	public static JPanel getPanel()
	{
		return pnlInhalt;
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
