package de.dhpoly.handel.model;

public enum TransaktionsTyp
{
	NEU(false, false, "Schlie�en"), // Leerer Vorschlag NOSONAR
	NEUER_VORSCHLAG(true, false, "Anbieten"), //
	VORSCHLAG(false, true, "Annehmen"), // Vorgeschlagen
	ANGENOMMEN(false, false, "Schlie�en"), //
	ABGELEHNT(false, false, "Schlie�en");

	boolean handelAnbieten;
	boolean handelAnnehmen;
	String beschreibung;

	private TransaktionsTyp(boolean handelAnbieten, boolean handelAnnehmen, String beschreibung)
	{
		this.handelAnbieten = handelAnbieten;
		this.handelAnnehmen = handelAnnehmen;
		this.beschreibung = beschreibung;
	}

	public boolean isHandelAnnehmen()
	{
		return handelAnnehmen;
	}

	public boolean isHandelAnbieten()
	{
		return handelAnbieten;
	}

	public String getBeschreibung()
	{
		return beschreibung;
	}
}
