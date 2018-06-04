package de.dhpoly.main;

import java.io.IOException;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.fakes.ClientFake;
import de.dhpoly.fakes.ServerFake;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.spiel.view.SpielerstellerUI;

public class MainFake
{
	public static void main(String[] args) throws IOException
	{
		new MainFake();
	}

	public MainFake() throws IOException
	{
		Fenster fenster = new Fenster(new Bilderverwalter());
		ClientFake client = ClientFake.CLIENT_FAKE;
		ServerFake server = ServerFake.SERVER_FAKE;
		new SpielerstellerUI(fenster, server, client);
	}
}
