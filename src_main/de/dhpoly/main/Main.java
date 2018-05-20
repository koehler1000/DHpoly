package de.dhpoly.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import de.dhpoly.ai.AI;
import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.netzwerk.NetzwerkServer;
import de.dhpoly.netzwerk.control.NetzwerkClientImpl;
import de.dhpoly.netzwerk.control.NetzwerkServerImpl;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.spiel.model.SpielDaten;
import de.dhpoly.spieler.model.Spieler;

public class Main implements Runnable
{
	private Fenster fenster = new Fenster(new Bilderverwalter());

	public static void main(String[] args) throws IOException
	{
		Main main = new Main();
		String ip = main.starteServer();
		main.starteClient(ip);

		String name;
		while ((name = JOptionPane.showInputDialog("Name für Computerspieler")) != "")
		{
			AI ai = new AI();
			ai.erzeugeComputerspieler(ip, name);
		}
	}

	private void starteClient(String ipHost) throws IOException
	{
		String str = JOptionPane.showInputDialog("IP Adresse", ipHost);

		NetzwerkClientImpl client = new NetzwerkClientImpl(null);
		client.verbinden(str, 3001);
		client.sendeAnServer("Test");

		NetzwerkClientImpl client2 = new NetzwerkClientImpl(null);
		client2.verbinden(str, 3001);
		client2.sendeAnServer("Test2");

		// NetzwerkClientImpl client = new NetzwerkClientImpl(str); // TODO ClientName
		// muss übergeben werden
		// client.connect(ipHost, 3001);
		//
		// Spieler spieler = new Spieler(SpielerTyp.LOKAL, "Netzwerkspieler");
		// client.sendeAnServer(spieler.toString()); // TODO spieler muss noch
		// serialisiert werden
		//
		// SpielfeldAnsicht ansicht = new SpielfeldAnsicht(spieler, client);
		// fenster.zeigeSpielansicht(ansicht, spieler.getName());
	}

	private String starteServer()
	{
		new Thread(new Main()).start();

		List<Spieler> spieler = new ArrayList<>();
		spieler.add(new Spieler("Rico"));
		spieler.add(new Spieler("Sven"));
		spieler.add(new Spieler("Alex"));

		SpielDaten daten = new SpielDaten(spieler, new Einstellungen());
		// server.sendeAnClients(daten);
		//
		// String ip = server.getIp();
		// JOptionPane.showMessageDialog(null, "Lade deine Freunde ein, mit auf " + ip +
		// " zu spielen");
		return "127.0.0.1"; // TODO richtige IP muss zurückgegeben werden
	}

	@Override
	public void run()
	{
		NetzwerkServer server = new NetzwerkServerImpl(3001);
		try
		{
			server.run(null);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
