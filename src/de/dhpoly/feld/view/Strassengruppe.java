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
		farbenZuordnungen.add(new Color(255, 155, 0));
		farbenZuordnungen.add(Color.YELLOW);
		farbenZuordnungen.add(Color.CYAN);
		farbenZuordnungen.add(new Color(150, 200, 250));
		farbenZuordnungen.add(new Color(255, 100, 100));
		farbenZuordnungen.add(Color.PINK);
		farbenZuordnungen.add(Color.GREEN);
		farbenZuordnungen.add(Color.LIGHT_GRAY);
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
