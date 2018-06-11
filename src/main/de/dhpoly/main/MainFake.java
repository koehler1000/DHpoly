package de.dhpoly.main;

import java.io.IOException;

import de.dhpoly.fakes.ServerFake;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.spiel.control.SpielImpl;
import de.dhpoly.spiel.view.SpielerErstellerUI;

public class MainFake
{
	public static void main(String[] args) throws IOException
	{
		new MainFake();
	}

	public MainFake() throws IOException
	{
		new SpielImpl(ServerFake.SERVER_FAKE);
		new SpielerErstellerUI(new Fenster());
	}
}
