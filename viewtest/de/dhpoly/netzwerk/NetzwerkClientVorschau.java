package de.dhpoly.netzwerk;

import de.dhpoly.bilderverwalter.Bilderverwalter;
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
		NetzwerkServer server = new NetzwerkServerImpl();
		NetzwerkClient client = new NetzwerkClientImpl("127.0.0.1");
		client.connectToServer();

		Fenster fenster = new Fenster(new Bilderverwalter());
		SpielfeldAnsicht ansicht = new SpielfeldAnsicht(new SpielerDaten(SpielerTyp.NETZWERK, "Netzwerkspieler"),
				client);
		fenster.zeigeSpielansicht(ansicht, "NetzwerkClientVorschau");

		client.addAnsicht(ansicht);
		server.sende("hallo");
	}
}
