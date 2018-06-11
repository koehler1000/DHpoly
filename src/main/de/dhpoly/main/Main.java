package de.dhpoly.main;

import java.io.IOException;

import de.dhpoly.fakes.ServerFake;
import de.dhpoly.oberflaeche.view.Fenster;
import de.dhpoly.spiel.control.SpielImpl;
import de.dhpoly.spiel.view.SpielerErstellerUI;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		new Main();
	}

	public Main() throws IOException
	{
		new SpielImpl(ServerFake.SERVER_FAKE);
		new SpielerErstellerUI(new Fenster());
	}
}
