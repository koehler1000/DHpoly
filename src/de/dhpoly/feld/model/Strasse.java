package de.dhpoly.feld.model;

import java.util.List;
import java.util.Optional;

import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.Spieler;

public class Strasse
{
	private Optional<Spieler> eigentuemer = Optional.empty();
	private int[] miete = new int[6];

	private int haueser = 0;
	private boolean hypothek = false;
	private List<RessourcenDatensatz> kostenHaus;
	private int gruppe;
	private String name;
	private int kaufpreis;

	public Optional<Spieler> getEigentuemer()
	{
		return eigentuemer;
	}

	public void setEigentuemer(Optional<Spieler> eigentuemer)
	{
		this.eigentuemer = eigentuemer;
	}

	public int[] getMiete()
	{
		return miete;
	}

	public void setMiete(int[] miete)
	{
		this.miete = miete;
	}

	public int getHaueser()
	{
		return haueser;
	}

	public void setHaueser(int haueser)
	{
		this.haueser = haueser;
	}

	public boolean isHypothek()
	{
		return hypothek;
	}

	public void setHypothek(boolean hypothek)
	{
		this.hypothek = hypothek;
	}

	public List<RessourcenDatensatz> getKostenHaus()
	{
		return kostenHaus;
	}

	public void setKostenHaus(List<RessourcenDatensatz> kostenHaus)
	{
		this.kostenHaus = kostenHaus;
	}

	public int getGruppe()
	{
		return gruppe;
	}

	public void setGruppe(int gruppe)
	{
		this.gruppe = gruppe;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getKaufpreis()
	{
		return kaufpreis;
	}

	public void setKaufpreis(int kaufpreis)
	{
		this.kaufpreis = kaufpreis;
	}
}
