package de.dhpoly.pause;

import de.dhpoly.spiel.view.SpielUIVerwalter;

public class Pause
{
	private Pause()
	{}

	public static void pause(int millis, SpielUIVerwalter verwalter)
	{
		if (verwalter.sollAnimiertAnzeigen())
		{
			try
			{
				Thread.sleep(millis); // NOSONAR
			}
			catch (InterruptedException ex) // NOSONAR
			{
				// ignorieren
			}
		}
	}
}
