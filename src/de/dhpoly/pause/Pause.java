package de.dhpoly.pause;

public class Pause
{
	private Pause()
	{}

	public static void pause(int millis, boolean animationenAnzeigen)
	{
		if (animationenAnzeigen)
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
