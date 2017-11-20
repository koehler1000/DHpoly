package de.dhpoly.feld.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Strassengruppe
{
	private List<Color> farbenZuordnungen = new ArrayList<>();

	public Strassengruppe()
	{
		farbenZuordnungen.add(Color.BLACK);
	}

	public Color getColor(int strassengruppe)
	{
		if (farbenZuordnungen.size() > strassengruppe)
		{
			return farbenZuordnungen.get(strassengruppe);
		}
		else
		{
			return Color.CYAN;
		}
	}
}
