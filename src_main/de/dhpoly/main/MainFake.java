package de.dhpoly.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.fakes.ClientFake;
import de.dhpoly.fakes.ServerFake;
import de.dhpoly.netzwerk.NetzwerkServer;
import de.dhpoly.netzwerk.control.NetzwerkServerImpl;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielImpl;
import de.dhpoly.spiel.model.SpielDaten;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.model.SpielerTyp;

public class MainFake implements Runnable
{
	private Fenster fenster = new Fenster(new Bilderverwalter());
	private ClientFake client = ClientFake.clientFake;
	private ServerFake server = ServerFake.serverfake;

	public static void main(String[] args) throws IOException
	{
		new MainFake();
	}

	public MainFake()
	{
		Spieler spieler = new Spieler(SpielerTyp.NETZWERK, "Peter");
		client.setDatenobjektverwalter(new SpielfeldAnsicht(spieler, client));

		Spiel spiel = new SpielImpl();
		server.setDatenobjektverwalter(spiel);
	}

	private String starteServer() throws IOException
	{
		new Thread(new MainFake()).start();

		List<Spieler> spieler = new ArrayList<>();
		spieler.add(new Spieler(SpielerTyp.LOKAL, "Rico"));
		spieler.add(new Spieler(SpielerTyp.LOKAL, "Sven"));
		spieler.add(new Spieler(SpielerTyp.COMPUTER, "Alex"));

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
