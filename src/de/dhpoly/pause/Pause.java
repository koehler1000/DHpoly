package de.dhpoly.pause;

import de.dhpoly.oberflaeche.Oberflaeche;

public class Pause
{
	private Pause()
	{}

	public static void pause(int millis)
	{
		if (Oberflaeche.getInstance().sollAnimiertAnzeigen())
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
