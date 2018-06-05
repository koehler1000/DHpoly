package de.dhpoly.main;

import java.io.IOException;

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
		new SpielerstellerUI(new Fenster());
	}
}
