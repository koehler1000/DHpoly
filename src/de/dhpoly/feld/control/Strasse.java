package de.dhpoly.feld.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.spieler.Spieler;

public class Strasse extends Observable implements Feld
{
	private Optional<Spieler> eigentuemer = Optional.ofNullable(null);
	private int[] miete = new int[6];

	private int haueser = 0;
	private boolean hypothek = false;
	private int seite;
	private int gruppe;
	private String name;
	private int kaufpreis;

	private Felderverwaltung strassenverwaltung;
	private List<Spieler> spieler = new ArrayList<>();

	public Strasse(Felderverwaltung strassenverwaltung, int kaufpreis, int[] miete, int seite, int gruppe, String name)
	{
		this.strassenverwaltung = strassenverwaltung;
		this.miete = miete;
		this.seite = seite;
		this.gruppe = gruppe;
		this.name = name;
		this.kaufpreis = kaufpreis;
	}

	@Override
	public void betreteFeld(Spieler spieler, int augensumme, Wetter wetter)
	{
		spielerBetrittFeld(spieler, wetter);
		this.spieler.add(spieler);
		setChanged();
		notifyObservers();
	}

	public void spielerBetrittFeld(Spieler spieler, Wetter wetter)
	{
		if (isVerkauft())
		{
			zahle(spieler, wetter);
		}
		else
		{
			spieler.zeigeKaufmoeglichkeit(this);
		}
	}

	public boolean isKaufbar()
	{
		return !isVerkauft();
	}

	public boolean isVerkauft()
	{
		return eigentuemer.isPresent();
	}

	public void kaufe(Spieler potentiellerKaeufer)
	{
		kaufe(potentiellerKaeufer, kaufpreis);
	}

	public void kaufe(Spieler potentiellerKaeufer, int betrag)
	{
		if (isKaufbar())
		{
			potentiellerKaeufer.auszahlen(betrag);
			eigentuemer = Optional.ofNullable(potentiellerKaeufer);

			setChanged();
			notifyObservers();
		}
	}

	private void zahle(Spieler zahlender, Wetter wetter)
	{
		if (!hypothek)
		{
			int wert = (int) (getMietkosten() * 1.0 / 100 * wetter.getMietbeeinflussung());
			zahlender.ueberweiseGeld(wert, eigentuemer.get());
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

	public void setEigentuemer(Spieler anbietender)
	{
		eigentuemer = Optional.ofNullable(anbietender);
	}

	@Override
	public void verlasseFeld(Spieler spieler)
	{
		this.spieler.remove(spieler);
		setChanged();
		notifyObservers();
	}

	@Override
	public String getBeschriftung()
	{
		return name;
	}

	@Override
	public List<Spieler> getSpielerAufFeld()
	{
		return spieler;
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
}
