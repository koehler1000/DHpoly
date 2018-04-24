package de.dhpoly.spielobjekte.fehler.model;

import de.dhpoly.datenobjekt.Datenobjekt;

public class Fehler extends Datenobjekt
{
	private static final long serialVersionUID = 1L;

	private String fehlertext;
	private FehlerTyp fehlertyp;

	public Fehler(String fehlertext, FehlerTyp fehlertyp)
	{
		this.fehlertext = fehlertext;
		this.fehlertyp = fehlertyp;
	}

	@Override
	public String getTitel()
	{
		return "Fehler";
	}

	public String getFehlertext()
	{
		return fehlertext;
	}

	public FehlerTyp getFehlertyp()
	{
		return fehlertyp;
	}
}
