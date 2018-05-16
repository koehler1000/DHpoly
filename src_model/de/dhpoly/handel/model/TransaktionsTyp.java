package de.dhpoly.handel.model;

public enum TransaktionsTyp
{
	NEU(false, false, "Anbieten"), // Leerer Vorschlag NOSONAR
	NEUER_VORSCHLAG(true, false, "Anbieten"), //
	VORSCHLAG(false, true, "Annehmen"), // Vorgeschlagen
	ANGENOMMEN(false, false, "Schlieﬂen"), //
	ABGELEHNT(false, false, "Schlieﬂen");

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
