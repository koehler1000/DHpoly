package de.dhpoly.feld.control;

import java.util.List;
import java.util.Optional;

import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.control.RessourcenDatensatzImpl;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.Spieler;

public class Strasse extends FeldImpl
{
	private Optional<Spieler> eigentuemer = Optional.ofNullable(null);
	private int[] miete = new int[6];

	private int haueser = 0;
	private boolean hypothek = false;
	private List<RessourcenDatensatz> kostenHaus;
	private int gruppe;
	private String name;
	private int kaufpreis;

	private Felderverwaltung strassenverwaltung;

	public Strasse(Felderverwaltung strassenverwaltung, int kaufpreis, int[] miete,
			List<RessourcenDatensatz> kostenHaus, int gruppe, String name)
	{
		super(name);
		this.strassenverwaltung = strassenverwaltung;
		this.miete = miete;
		this.kostenHaus = kostenHaus;
		this.gruppe = gruppe;
		this.name = name;
		this.kaufpreis = kaufpreis;
	}

	@Override
	public void spielerBetrittFeld(Spieler spieler, int augensumme, Wetter wetter)
	{
		spielerBetrittFeld(spieler, wetter);
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
			potentiellerKaeufer.auszahlen(new RessourcenDatensatzImpl(Ressource.GELD, betrag));
			eigentuemer = Optional.ofNullable(potentiellerKaeufer);

			informiereBeobachter();
		}
	}

	private void zahle(Spieler zahlender, Wetter wetter)
	{
		if (!hypothek)
		{
			zahlender.ueberweise(new RessourcenDatensatzImpl(Ressource.GELD, getAkuelleMiete(wetter)),
					eigentuemer.get());
		}
	}

	public int getAkuelleMiete()
	{
		return (int) (getMietkosten() * 1.0 / 100);
	}

	public int getAkuelleMiete(Wetter wetter)
	{
		return (int) (getMietkosten() * wetter.getMietbeeinflussung());
	}

	private int getMietkosten()
	{
		if (eigentuemer.isPresent())
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
		return 0;
	}

	public void setEigentuemer(Spieler anbietender)
	{
		eigentuemer = Optional.ofNullable(anbietender);
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

	public List<RessourcenDatensatz> getKostenHaus()
	{
		return kostenHaus;
	}

	public boolean gehoertSpieler(Spieler spieler)
	{
		return (isVerkauft() && eigentuemer.get() == spieler);
	}
}
