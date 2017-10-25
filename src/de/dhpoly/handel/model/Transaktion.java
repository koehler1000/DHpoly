package de.dhpoly.handel.model;

import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.player.Player;

public class Transaktion
{
	private int geldbetrag = 0;
	private List<Feld> felderGeben;
	private List<Feld> felderBekommen;

	private Player anbietender;
	private Player handelspartner;

	public Transaktion(int geldbetrag, List<Feld> felderGeben, List<Feld> felderBekommen, Player anbietender,
			Player handelspartner)
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

	public Player getAnbietender()
	{
		return anbietender;
	}

	public Player getHandelspartner()
	{
		return handelspartner;
	}

}
