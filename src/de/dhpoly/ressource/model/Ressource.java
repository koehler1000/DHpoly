package de.dhpoly.ressource.model;

public enum Ressource
{
	HOLZ("Holz", "Hölzer"), STEIN("Stein", "Steine"), GELD("Euro", "€");

	private String einzahl;
	private String mehrzahl;

	private Ressource(String einzahl, String mehrzahl)
	{
		this.einzahl = einzahl;
		this.mehrzahl = mehrzahl;
	}

	public String getString(int anzahl)
	{
		if (anzahl == 1)
		{
			return "1 " + einzahl;
		}
		else
		{
			return "" + anzahl + mehrzahl;
		}
	}

	public static String getString(Ressource ressource, int anzahl)
	{
		return ressource.getString(anzahl);
	}
}
