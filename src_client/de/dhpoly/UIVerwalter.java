package de.dhpoly;

import java.io.IOException;

import de.dhpoly.netzwerk.NetzwerkClient;
import de.dhpoly.netzwerk.control.NetzwerkClientImpl;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spieler.model.SpielerDaten;
import de.dhpoly.spieler.model.SpielerTyp;

public class UIVerwalter
{
	private NetzwerkClient client;

	public UIVerwalter(String str) throws IOException
	{
		client = new NetzwerkClientImpl();

		SpielerDaten spieler = new SpielerDaten(SpielerTyp.LOKAL, "Netzwerkspieler");
		client.sende(spieler);

		SpielfeldAnsicht ansicht = new SpielfeldAnsicht(spieler, client);
	}
}
