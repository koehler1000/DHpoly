package de.dhpoly.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.netzwerk.NetzwerkClient;
import de.dhpoly.netzwerk.NetzwerkServer;
import de.dhpoly.netzwerk.control.NetzwerkClientImpl;
import de.dhpoly.netzwerk.control.NetzwerkServerImpl;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spiel.model.SpielDaten;
import de.dhpoly.spieler.model.SpielerDaten;
import de.dhpoly.spieler.model.SpielerTyp;

public class Main
{
	private Fenster fenster = new Fenster(new Bilderverwalter());

	public static void main(String[] args) throws IOException
	{
		Main main = new Main();
		String ip = main.starteServer();
		main.starteClient(ip);
	}

	private void starteClient(String ipHost) throws IOException
	{
		String str = JOptionPane.showInputDialog("IP Adresse", ipHost);

		NetzwerkClient client = new NetzwerkClientImpl(str);

		SpielerDaten spieler = new SpielerDaten(SpielerTyp.LOKAL, "Netzwerkspieler");
		client.sende(spieler);

		SpielfeldAnsicht ansicht = new SpielfeldAnsicht(spieler, client);
		fenster.zeigeSpielansicht(ansicht, spieler.getName());
	}

	private String starteServer() throws IOException
	{
		NetzwerkServer server = new NetzwerkServerImpl();

		List<SpielerDaten> spieler = new ArrayList<>();
		spieler.add(new SpielerDaten(SpielerTyp.LOKAL, "Rico"));
		spieler.add(new SpielerDaten(SpielerTyp.LOKAL, "Sven"));
		spieler.add(new SpielerDaten(SpielerTyp.COMPUTER, "Alex"));

		SpielDaten daten = new SpielDaten(spieler, new Einstellungen());
		server.sendeAnClients(daten);

		String ip = server.getIp();
		JOptionPane.showMessageDialog(null, "Lade deine Freunde ein, mit auf " + ip + " zu spielen");
		return ip;
	}
}
