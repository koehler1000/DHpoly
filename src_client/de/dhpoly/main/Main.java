package de.dhpoly.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.main.view.MainUI;
import de.dhpoly.netzwerk.NetzwerkServer;
import de.dhpoly.netzwerk.control.NetzwerkServerImpl;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.spiel.model.SpielDaten;
import de.dhpoly.spieler.model.SpielerDaten;
import de.dhpoly.spieler.model.SpielerTyp;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		new Main().starteServer();
		new MainUI(new Fenster(new Bilderverwalter()));
	}

	private void starteServer() throws IOException
	{
		// TODO Server starten
		NetzwerkServer server = new NetzwerkServerImpl();

		List<SpielerDaten> spieler = new ArrayList<>();
		spieler.add(new SpielerDaten(SpielerTyp.LOKAL, "Rico"));
		spieler.add(new SpielerDaten(SpielerTyp.LOKAL, "Sven"));
		spieler.add(new SpielerDaten(SpielerTyp.COMPUTER, "Alex"));

		SpielDaten daten = new SpielDaten(spieler, new Einstellungen());
		server.sende(daten);

		// TODO IP-Adresse verwenden
		String ip = ""; // = server.getIP();
		JOptionPane.showMessageDialog(null, "Lade deine Freunde ein, mit auf " + ip + " zu spielen");
	}
}
