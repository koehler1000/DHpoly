package de.dhpoly.spielobjekte.fehler.model;

public enum FehlerTyp
{
	FEHLER_ENTWICKLER(true, false, false), // Fehler nur an Entwickler
	FEHLER_SPIELER(false, false, true), // Fehler nur an den aktuellen Spieler
	FEHLER_ALLE_SPIELER(false, true, true), // Fehler an alle Spieler
	FEHLER_ALLE(true, true, true) // Fehler an alle Spieler und Entwickler
	;

	private boolean entwicklerInformieren;
	private boolean alleSpielerInformieren;
	private boolean aktuellenSpielerInformieren;

	private FehlerTyp(boolean entwicklerInformieren, boolean alleSpielerInformieren,
			boolean aktuellenSpielerInformieren)
	{
		this.entwicklerInformieren = entwicklerInformieren;
		this.alleSpielerInformieren = alleSpielerInformieren;
		this.aktuellenSpielerInformieren = aktuellenSpielerInformieren;
	}

	public boolean isEntwicklerInformieren()
	{
		return entwicklerInformieren;
	}

	public boolean isAlleSpielerInformieren()
	{
		return alleSpielerInformieren;
	}

	public boolean isAktuellenSpielerInformieren()
	{
		return aktuellenSpielerInformieren;
	}
}
