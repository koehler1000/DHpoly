package de.dhpoly.main;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.main.view.MainUI;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.spiel.model.SpielDaten;
import de.dhpoly.spieler.model.SpielerDaten;
import de.dhpoly.spieler.model.SpielerTyp;

public class Main
{
	public static void main(String[] args)
	{
		new Main().starteServer();
		new MainUI(new Fenster(new Bilderverwalter()));
	}

	private void starteServer()
	{
		// TODO Server starten

		List<SpielerDaten> spieler = new ArrayList<>();
		spieler.add(new SpielerDaten(SpielerTyp.LOKAL, "Rico"));
		spieler.add(new SpielerDaten(SpielerTyp.LOKAL, "Sven"));
		spieler.add(new SpielerDaten(SpielerTyp.COMPUTER, "Alex"));

		SpielDaten daten = new SpielDaten(spieler, new Einstellungen());
		// server.senden(daten);
	}
}
