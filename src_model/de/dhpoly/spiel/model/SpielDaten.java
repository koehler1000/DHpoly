package de.dhpoly.spiel.model;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.spieler.model.SpielerDaten;

public class SpielDaten extends Datenobjekt
{
	private static final long serialVersionUID = 1L;

	private List<SpielerDaten> spieler = new ArrayList<>();
	private Einstellungen einstellungen = new Einstellungen();

	public SpielDaten(List<SpielerDaten> spieler, Einstellungen einstellungen)
	{
		super();
		this.spieler = spieler;
		this.einstellungen = einstellungen;
	}

	public List<SpielerDaten> getSpieler()
	{
		return spieler;
	}

	public Einstellungen getEinstellungen()
	{
		return einstellungen;
	}

	@Override
	public String getTitel()
	{
		return "Spiel";
	}
}
