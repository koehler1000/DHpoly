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
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.model.SpielerTyp;

public class Main implements Runnable
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
		
		NetzwerkClientImpl client = new NetzwerkClientImpl(); //TODO ClientName muss �bergeben werden
		client.connect(ipHost, 3001);

		Spiele spieler = new SpielerDaten(SpielerTyp.LOKAL, "Netzwerkspieler");
		client.sendeAnServer(spieler.toString()); //TODO spieler muss noch serialisiert werden

		SpielfeldAnsicht ansicht = new SpielfeldAnsicht(spieler, client);
		fenster.zeigeSpielansicht(ansicht, spieler.getName());
	}

	private String starteServer() throws IOException
	{
		new Thread(new Main()).start();

		List<Spieler> spieler = new ArrayList<>();
		spieler.add(new SpielerDaten(SpielerTyp.LOKAL, "Rico"));
		spieler.add(new SpielerDaten(SpielerTyp.LOKAL, "Sven"));
		spieler.add(new SpielerDaten(SpielerTyp.COMPUTER, "Alex"));

		SpielDaten daten = new SpielDaten(spieler, new Einstellungen());
//		server.sendeAnClients(daten);
//
//		String ip = server.getIp();
//		JOptionPane.showMessageDialog(null, "Lade deine Freunde ein, mit auf " + ip + " zu spielen");
		return "127.0.0.1"; //TODO richtige IP muss zur�ckgegeben werden
	}

	@Override
	public void run() {
		NetzwerkServer server = new NetzwerkServerImpl(3001);
		try {
			server.run(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
