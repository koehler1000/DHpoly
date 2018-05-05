package de.dhpoly.feld.model;

import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.spieler.model.Spieler;

public class Bewegung extends Datenobjekt
{
	private static final long serialVersionUID = 1L;

	private Spieler spieler;
	private int felder;
	private FeldDaten startfeld;
	private List<FeldDaten> ueberlaufeneFelder;

	public Bewegung(Spieler spieler, int felder, FeldDaten startfeld, List<FeldDaten> ueberlaufeneFelder)
	{
		super();
		this.spieler = spieler;
		this.felder = felder;
		this.startfeld = startfeld;
		this.ueberlaufeneFelder = ueberlaufeneFelder;
	}

	public Spieler getSpieler()
	{
		return spieler;
	}

	public int getFelder()
	{
		return felder;
	}

	public FeldDaten getStartfeld()
	{
		return startfeld;
	}

	public List<FeldDaten> getUeberlaufeneFelder()
	{
		return ueberlaufeneFelder;
	}

	@Override
	public String getTitel()
	{
		return "Bewegung";
	}

	@Override
	public Class<? extends Oberflaeche> getClassUI()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
