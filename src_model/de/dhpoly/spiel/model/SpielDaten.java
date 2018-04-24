package de.dhpoly.spiel.model;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.einstellungen.model.Einstellungen;
import de.dhpoly.spieler.Spieler;

public class SpielDaten extends Datenobjekt
{
	private static final long serialVersionUID = 1L;

	private List<Spieler> spieler = new ArrayList<>();
	private Einstellungen einstellungen = new Einstellungen();

	public SpielDaten(List<Spieler> spieler, Einstellungen einstellungen)
	{
		super();
		this.spieler = spieler;
		this.einstellungen = einstellungen;
	}

	public List<Spieler> getSpieler()
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
