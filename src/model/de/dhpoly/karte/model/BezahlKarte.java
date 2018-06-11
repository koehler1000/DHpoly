package de.dhpoly.karte.model;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.karte.view.KarteUI;
import de.dhpoly.kartenstapel.model.BezahlZiel;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.ressource.model.RessourcenDatensatz;

public class BezahlKarte extends Datenobjekt implements Karte
{
	private static final long serialVersionUID = 1L;

	private String beschreibung;
	private List<RessourcenDatensatz> ressourcenDatensaetze;

	private BezahlZiel geldQuelle;

	private BezahlZiel geldZiel;

	public BezahlKarte(String beschreibung, BezahlZiel quelle, BezahlZiel ziel,
			List<RessourcenDatensatz> ressourcenDatensaetze)
	{
		super();
		this.beschreibung = beschreibung;
		this.geldQuelle = quelle;
		this.geldZiel = ziel;
		this.ressourcenDatensaetze = ressourcenDatensaetze;
	}

	public BezahlKarte(String beschreibung, BezahlZiel quelle, BezahlZiel ziel, RessourcenDatensatz datensatz)
	{
		this(beschreibung, quelle, ziel, new ArrayList<>());
		ressourcenDatensaetze.add(datensatz);
	}

	public String getBeschreibung()
	{
		return beschreibung;
	}

	public BezahlZiel getGeldQuelle()
	{
		return geldQuelle;
	}

	public BezahlZiel getGeldZiel()
	{
		return geldZiel;
	}

	public List<RessourcenDatensatz> getRessourcenDatensaetze()
	{
		return ressourcenDatensaetze;
	}

	@Override
	public String getTitel()
	{
		return "Bezahlkarte";
	}

	@Override
	public Class<? extends Oberflaeche> getClassUI()
	{
		return KarteUI.class;
	}
}
