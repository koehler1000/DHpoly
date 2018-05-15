package de.dhpoly.feld.model;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.oberflaeche.view.Oberflaeche;

public class Hausbau extends Datenobjekt
{
	private static final long serialVersionUID = 1L;

	private StrasseDaten strasse;
	private int anzahl;

	public Hausbau(StrasseDaten strasse, int anzahl)
	{
		super();
		this.strasse = strasse;
		this.anzahl = anzahl;
	}

	public StrasseDaten getStrasse()
	{
		return strasse;
	}

	public int getAnzahl()
	{
		return anzahl;
	}

	@Override
	public String getTitel()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends Oberflaeche> getClassUI()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
