package de.dhpoly.spieler.view;

import java.awt.Color;
import java.util.Optional;

import de.dhpoly.spieler.model.Spieler;

public class SpielerFarben
{
	private static final Color DEFAULTFARBE = Color.WHITE;

	private SpielerFarben()
	{}

	private static Color[] farben = new Color[] { new Color(255, 155, 0), Color.YELLOW, Color.GREEN,
			new Color(255, 50, 50), new Color(50, 50, 255), new Color(50, 255, 50) };

	public static Color getSpielerfarbe(int spielerID)
	{
		if (farben.length > spielerID && spielerID >= 0)
		{
			return farben[spielerID];
		}
		return Color.CYAN;
	}

	public static Color getSpielerfarbe(Spieler spieler)
	{
		return getSpielerfarbe(spieler.getSpielerNr());
	}

	public static Color getSpielerfarbe(Optional<Spieler> eigentuemer)
	{
		return eigentuemer.isPresent() ? getSpielerfarbe(eigentuemer.get()) : DEFAULTFARBE;
	}
}
