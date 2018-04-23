package de.dhpoly.ressource.model;

public enum Ressource
{
	HOLZ("Holz", "Hölzer", "Wald"), //
	STEIN("Stein", "Steine", "Steinbruch"), //
	GELD("€", "€", "Tresor");

	private String einzahl;
	private String mehrzahl;
	private String ressourcenquelle;

	private Ressource(String einzahl, String mehrzahl, String ressourcenquelle)
	{
		this.einzahl = einzahl;
		this.mehrzahl = mehrzahl;
		this.ressourcenquelle = ressourcenquelle;
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
}
