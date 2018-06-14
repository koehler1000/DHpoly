package de.dhpoly.main;

import de.dhpoly.fakes.Server;
import de.dhpoly.netzwerk.NetzwerkServer;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.spiel.control.SpielImpl;
import de.dhpoly.spiel.view.SpielerErstellerUI;

public class Main
{
	public static void main(String[] args)
	{
		new Main();
	}

	public Main()
	{
		NetzwerkServer server = Server.erzeugeServer();
		new SpielImpl(server);
		new SpielerErstellerUI(new Fenster(), server);
	}
}
