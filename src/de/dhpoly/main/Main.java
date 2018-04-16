package de.dhpoly.main;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.main.view.MainUI;
import de.dhpoly.oberflaeche.view.Fenster;

public class Main
{
	public static void main(String[] args)
	{
		new MainUI(new Fenster(new Bilderverwalter()));
	}
}
