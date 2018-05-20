package de.dhpoly.empfaenger.model;

public enum Empfaenger
{
	ENTWICKLER(true, false, false), // Fehler nur an Entwickler
	AKTUELLER_SPIELER(false, false, true), // Fehler nur an den aktuellen Spieler
	ALLE_SPIELER(false, true, true), // Fehler an alle Spieler
	ALLE(true, true, true) // Fehler an alle Spieler und Entwickler
	;

	private boolean entwicklerInformieren;
	private boolean alleSpielerInformieren;
	private boolean aktuellenSpielerInformieren;

	private Empfaenger(boolean entwicklerInformieren, boolean alleSpielerInformieren,
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
