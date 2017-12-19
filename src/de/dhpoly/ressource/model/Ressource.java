package de.dhpoly.ressource.model;

public enum Ressource
{
	HOLZ("Holz", "Hölzer"), STEIN("Stein", "Steine"), GELD("€", "€");

	private String einzahl;
	private String mehrzahl;

	private Ressource(String einzahl, String mehrzahl)
	{
		this.einzahl = einzahl;
		this.mehrzahl = mehrzahl;
	}

	public String getString(int anzahl)
	{
		return "" + anzahl + " " + ((anzahl == 1 || anzahl == -1) ? einzahl : mehrzahl);
	}

	public static String getString(Ressource ressource, int anzahl)
	{
		return ressource.getString(anzahl);
	}
}
