package de.dhpoly.netzwerk.view;

import java.io.IOException;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.fehler.model.Fehler;
import de.dhpoly.fehler.model.FehlerTyp;
import de.dhpoly.netzwerk.NetzwerkClient;
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
		Fenster fenster = new Fenster(new Bilderverwalter());
		NetzwerkClient client = new NetzwerkClientImpl("127.0.0.1");

		SpielfeldAnsicht ansicht = new SpielfeldAnsicht(new SpielerDaten(SpielerTyp.NETZWERK, "Netzwerkspieler"),
				client);
		client.addAnsicht(ansicht);
		fenster.zeigeSpielansicht(ansicht, "NetzwerkClientVorschau");

		NetzwerkServerImpl server = new NetzwerkServerImpl();
		Thread x = new Thread(() -> {
			try
			{
				server.run();
			}
			catch (IOException e)
			{
				Fehler fehler = new Fehler(e.getMessage(), FehlerTyp.FEHLER_ALLE);
				fehler.anzeigen(ansicht);
			}

		});
		x.start();
		client.verbindungAufbauen();

	}
}
