package de.dhpoly.spieler.view;

import java.awt.Color;

public class SpielerFarben
{
	private static Color[] farben = new Color[] { Color.RED, Color.BLUE, Color.GREEN };

	public static Color getSpielerfarbe(int spielerID)
	{
		if (farben.length > spielerID && spielerID >= 0)
		{
			return farben[spielerID];
		}
		return Color.CYAN;
	}
}
