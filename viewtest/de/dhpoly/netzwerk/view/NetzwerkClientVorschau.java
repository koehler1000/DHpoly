package de.dhpoly.netzwerk.view;

import java.io.IOException;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.netzwerk.NetzwerkClient;
import de.dhpoly.netzwerk.NetzwerkServer;
import de.dhpoly.netzwerk.control.NetzwerkClientImpl;
import de.dhpoly.netzwerk.control.NetzwerkServerImpl;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spieler.model.SpielerDaten;
import de.dhpoly.spieler.model.SpielerTyp;

public class NetzwerkClientVorschau
{
	public static void main(String[] args) throws Exception
	{
		NetzwerkServerImpl server = new NetzwerkServerImpl();
		Thread x = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					server.run();
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		x.start();
		NetzwerkClient client = new NetzwerkClientImpl("127.0.0.1");
		client.verbindungAufbauen();

		Fenster fenster = new Fenster(new Bilderverwalter());
		SpielfeldAnsicht ansicht = new SpielfeldAnsicht(new SpielerDaten(SpielerTyp.NETZWERK, "Netzwerkspieler"),
				client);
		fenster.zeigeSpielansicht(ansicht, "NetzwerkClientVorschau");

		client.addAnsicht(ansicht);
	}
}
