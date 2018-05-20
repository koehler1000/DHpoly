package de.dhpoly.fehler.model;

import de.dhpoly.datenobjekt.DatenobjektAnClient;
import de.dhpoly.empfaenger.model.Empfaenger;
import de.dhpoly.fehler.view.FehlerUI;
import de.dhpoly.oberflaeche.view.Oberflaeche;

public class Fehler extends DatenobjektAnClient
{
	private static final long serialVersionUID = 1L;

	private String fehlertext;
	private Empfaenger fehlertyp;

	public Fehler(String fehlertext, Empfaenger fehlertyp)
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

	public Empfaenger getFehlertyp()
	{
		return fehlertyp;
	}

	@Override
	public Class<? extends Oberflaeche> getClassUI()
	{
		return FehlerUI.class;
	}
}
