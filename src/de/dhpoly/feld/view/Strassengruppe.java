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
		farbenZuordnungen.add(Color.GRAY);
		farbenZuordnungen.add(Color.YELLOW);
		farbenZuordnungen.add(Color.CYAN);
		farbenZuordnungen.add(Color.BLUE);
		farbenZuordnungen.add(Color.RED);
		farbenZuordnungen.add(Color.PINK);
		farbenZuordnungen.add(Color.GREEN);
		farbenZuordnungen.add(Color.BLUE);
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
