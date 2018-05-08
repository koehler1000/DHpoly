package de.dhpoly.main;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.fakes.ClientFake;
import de.dhpoly.fakes.ServerFake;
import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielImpl;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.model.SpielerTyp;

public class MainFake
{
	private Fenster fenster = new Fenster(new Bilderverwalter());
	private ClientFake client = ClientFake.clientFake;
	private ServerFake server = ServerFake.serverfake;

	public static void main(String[] args)
	{
		new MainFake();
	}

	public MainFake()
	{
		Spieler spieler = new Spieler(SpielerTyp.NETZWERK, "Peter");

		SpielfeldAnsicht verwalter = new SpielfeldAnsicht(spieler, client);
		client.setDatenobjektverwalter(verwalter);
		fenster.zeigeSpielansicht(verwalter, "Fake");

		Spiel spiel = new SpielImpl(server);
		spiel.fuegeSpielerHinzu(spieler);
		server.setDatenobjektverwalter(spiel);

		spiel.starteSpiel();

		server.sendeAnSpieler(new Nachricht("Fake läuft"));
		server.sendeAnSpieler(spiel.getAktuellerSpieler());
	}
}
