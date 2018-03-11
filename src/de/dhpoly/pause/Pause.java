package de.dhpoly.pause;

public class Pause
{
	private Pause()
	{}

	public void pause(int millis)
	{
		if (true) // FIXME: Nur, wenn in Einstellungen Animationen animiert
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
