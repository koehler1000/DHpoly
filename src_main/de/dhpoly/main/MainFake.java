package de.dhpoly.main;

import java.io.IOException;

import de.dhpoly.ai.AI;
import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.fakes.ClientFake;
import de.dhpoly.fakes.ServerFake;
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
	private ServerFake server = ServerFake.SERVERFAKE;

	public static void main(String[] args) throws IOException
	{
		new MainFake();
	}

	public MainFake() throws IOException
	{
		Spieler spieler = new Spieler(SpielerTyp.NETZWERK, "Peter");

		SpielfeldAnsicht verwalter = new SpielfeldAnsicht(spieler, client);
		fenster.zeigeSpielansicht(verwalter, "Fake");

		Spiel spiel = new SpielImpl(server);
		spiel.fuegeSpielerHinzu(spieler);

		new AI().erzeugeComputerspieler(client, "PC1");
	}
}
