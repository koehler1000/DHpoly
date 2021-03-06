package de.dhpoly.feld.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.dhpoly.feld.view.StrasseInfoUI;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.model.Spieler;

public class StrasseDaten extends FeldDaten
{
	private static final long serialVersionUID = 2L;

	private Spieler eigentuemer = null;
	private int[] miete = new int[6];

	private int haueser = 0;
	private boolean hypothek = false;
	private List<RessourcenDatensatz> kostenHaus = new ArrayList<>();
	private int gruppe = 0;
	private int strassenAnzahlInGruppe = 1;
	private String name = "Teststraße";
	private int kaufpreis = 0;

	public StrasseDaten(int i, int[] js, List<RessourcenDatensatz> list, int j, String string)
	{
		super(FeldTyp.STRASSE);
		kaufpreis = i;
		miete = js;
		kostenHaus = list;
		gruppe = j;
		name = string;
	}

	public StrasseDaten()
	{
		super(FeldTyp.STRASSE);
	}

	public Optional<Spieler> getEigentuemer()
	{
		return Optional.ofNullable(eigentuemer);
	}

	public void setEigentuemer(Spieler eigentuemer)
	{
		this.eigentuemer = eigentuemer;
	}

	public void setEigentuemer(Optional<Spieler> eigentuemer)
	{
		this.eigentuemer = eigentuemer.get(); // NOSONAR weil Optional nicht Serialisierbar ist
	}

	public int[] getMiete()
	{
		return miete;
	}

	public int getMiete(int haeuser)
	{
		return miete[haeuser];
	}

	public void setMiete(int[] miete)
	{
		this.miete = miete;
	}

	public int getHaeuser()
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

	@Override
	public String getTitel()
	{
		return "Informationen zu " + getName();
	}

	public int getAkuelleMiete()
	{
		return getMiete(haueser);
	}

	public boolean isEigentuemer(Spieler spieler)
	{
		return spieler.equals(eigentuemer);
	}

	public boolean isAlleHaeuserGebaut()
	{
		return haueser + 1 >= miete.length;
	}

	public boolean haeuserGebaut()
	{
		return getHaeuser() > 0;
	}

	public int getStrassenAnzahlInGruppe()
	{
		return strassenAnzahlInGruppe;
	}

	public void setStrassenAnzahlInGruppe(int strassenAnzahlInGruppe)
	{
		this.strassenAnzahlInGruppe = strassenAnzahlInGruppe;
	}

	@Override
	public Class<? extends Oberflaeche> getClassUI()
	{
		return StrasseInfoUI.class;
	}

	@Override
	public boolean gehoertSpieler(Spieler spieler)
	{
		return spieler.equals(eigentuemer);
	}
}
