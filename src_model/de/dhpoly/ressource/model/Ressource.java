package de.dhpoly.ressource.model;

import java.awt.Color;

public enum Ressource
{
	HOLZ("Holz", "Hölzer", "Wald", Color.getHSBColor(33, 94, 78)), //
	STEIN("Stein", "Steine", "Steinbruch", Color.getHSBColor(220, 220, 220)), //
	GELD("€", "€", "Tresor", Color.WHITE);

	private String einzahl;
	private String mehrzahl;
	private String ressourcenquelle;
	private Color color;

	private Ressource(String einzahl, String mehrzahl, String ressourcenquelle, Color color)
	{
		this.einzahl = einzahl;
		this.mehrzahl = mehrzahl;
		this.ressourcenquelle = ressourcenquelle;
		this.color = color;
	}

	public String getString(int anzahl)
	{
		return "" + anzahl + " " + ((anzahl == 1 || anzahl == -1) ? einzahl : mehrzahl);
	}

	public String getRessourcenFeldString()
	{
		return "Gefunden im " + ressourcenquelle;
	}

	public static String getString(Ressource ressource, int anzahl)
	{
		return ressource.getString(anzahl);
	}

	public Color getFarbe()
	{
		return color;
	}
}
