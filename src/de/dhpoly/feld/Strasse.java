package de.dhpoly.feld;

import java.util.Optional;

import de.dhpoly.spieler.Spieler;

public class Strasse extends Feld
{
	private Optional<Spieler> eigentuemer = Optional.ofNullable(null);
	private int haueser = 0;
	private boolean hypothek = false;
	private int[] miete = new int[6];
	private int seite;
	private int gruppe;
	private String name;
	private int kaufpreis;

	private Felderverwaltung strassenverwaltung;

	public Strasse(Felderverwaltung strassenverwaltung, int kaufpreis, int[] miete, int seite, int gruppe, String name)
	{
		this.strassenverwaltung = strassenverwaltung;
		this.miete = miete;
		this.seite = seite;
		this.gruppe = gruppe;
		this.name = name;
		this.kaufpreis = kaufpreis;
	}

	public boolean isKaufbar()
	{
		return !eigentuemer.isPresent();
	}

	public void kaufe(Spieler potentiellerKaeufer)
	{
		if (isKaufbar())
		{
			potentiellerKaeufer.auszahlen(kaufpreis);
			eigentuemer = Optional.ofNullable(potentiellerKaeufer);
		}
	}

	public void kaufe(Spieler potentiellerKaeufer, int betrag)
	{
		if (isKaufbar())
		{
			potentiellerKaeufer.auszahlen(betrag);
			eigentuemer = Optional.ofNullable(potentiellerKaeufer);
		}
	}

	public void spielerBetrittFeld(Spieler kasse)
	{
		eigentuemer.ifPresent(eigentuemer -> zahle(kasse));
	}

	private void zahle(Spieler zahlender)
	{
		if (!hypothek)
		{
			zahlender.ueberweiseGeld(getMietkosten(), eigentuemer.get());
		}
	}

	private int getMietkosten()
	{
		if (strassenverwaltung.isNutzerBesitzerAllerStrassen(gruppe, eigentuemer.get()) && haueser == 0)
		{
			return miete[0] * 2;
		}
		else
		{
			return miete[haueser];
		}
	}

	public Optional<Spieler> getEigentuemer()
	{
		return eigentuemer;
	}

	public int getHaueser()
	{
		return haueser;
	}

	public boolean isHypothek()
	{
		return hypothek;
	}

	public int[] getMiete()
	{
		return miete;
	}

	public int getSeite()
	{
		return seite;
	}

	public int getGruppe()
	{
		return gruppe;
	}

	public String getName()
	{
		return name;
	}

	public int getKaufpreis()
	{
		return kaufpreis;
	}

	public Felderverwaltung getStrassenverwaltung()
	{
		return strassenverwaltung;
	}

	@Override
	public void betreteFeld(Spieler spieler, int augensumme)
	{
		spielerBetrittFeld(spieler);
	}

}
