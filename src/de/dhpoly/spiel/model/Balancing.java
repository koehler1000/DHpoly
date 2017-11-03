package de.dhpoly.spiel.model;

public enum Balancing
{
	STARTGUTHABEN(1000), //
	UEBER_LOS(100), //
	RESSOURCENERTRAG(1), // je Runde
	KOSTEN_HAUS_GELD(50), // Seite 1 (Seite 2 = Seite 1 * 2)
	KOSTEN_HAUS_HOLZ(10), // Seite 1 (Seite 2 = Seite 1 * 2)
	KOSTEN_HAUS_STEIN(10), // Seite 1 (Seite 2 = Seite 1 * 2)
	;

	private int wert;

	private Balancing(int wert)
	{
		this.wert = wert;
	}

	public int getWert()
	{
		return wert;
	}

	public void setWert(int wert)
	{
		this.wert = wert;
	}

}
