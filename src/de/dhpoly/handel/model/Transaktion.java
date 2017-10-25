package de.dhpoly.handel.model;

import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.spieler.Spieler;

public class Transaktion
{
	private int geldbetrag = 0;
	private List<Feld> felderGeben;
	private List<Feld> felderBekommen;

	private Spieler anbietender;
	private Spieler handelspartner;

	public Transaktion(int geldbetrag, List<Feld> felderGeben, List<Feld> felderBekommen, Spieler anbietender,
			Spieler handelspartner)
	{
		super();
		this.geldbetrag = geldbetrag;
		this.felderGeben = felderGeben;
		this.felderBekommen = felderBekommen;
		this.anbietender = anbietender;
		this.handelspartner = handelspartner;
	}

	public int getGeldbetrag()
	{
		return geldbetrag;
	}

	public List<Feld> getFelderGeben()
	{
		return felderGeben;
	}

	public List<Feld> getFelderBekommen()
	{
		return felderBekommen;
	}

	public Spieler getAnbietender()
	{
		return anbietender;
	}

	public Spieler getHandelspartner()
	{
		return handelspartner;
	}

}
