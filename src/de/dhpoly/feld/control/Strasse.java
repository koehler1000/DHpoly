package de.dhpoly.feld.control;

import java.util.List;
import java.util.Optional;

import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.Spieler;

public class Strasse extends FeldImpl
{
	private Optional<Spieler> eigentuemer = Optional.empty();
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

	@Override
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
			potentiellerKaeufer.auszahlen(new RessourcenDatensatz(Ressource.GELD, betrag, "Kauf: " + this.getName()));
			setEigentuemer(potentiellerKaeufer);

			informiereBeobachter();
		}
	}

	private void zahle(Spieler zahlender, Wetter wetter)
	{
		eigentuemer.ifPresent(besitzer -> {
			if (!hypothek)
			{
				zahlender.ueberweise(getMietDatensatz(wetter), besitzer);
			}
		});
	}

	private RessourcenDatensatz getMietDatensatz(Wetter wetter)
	{
		int aktuelleMiete = getAkuelleMiete(wetter);
		return new RessourcenDatensatz(Ressource.GELD, aktuelleMiete, "Miete");
	}

	public int getAkuelleMiete()
	{
		return getMietkosten();
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
		informiereBeobachter();
	}

	public Optional<Spieler> getEigentuemer()
	{
		return eigentuemer;
	}

	public int getHaeuser()
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

	@Override
	public boolean gehoertSpieler(Spieler spieler)
	{
		return eigentuemer.isPresent() && eigentuemer.get() == spieler;
	}

	public void hausBauen()
	{
		if (haueser < miete.length - 1 && eigentuemer.isPresent() && eigentuemer.get().kannBezahlen(kostenHaus))
		{
			eigentuemer.get().auszahlen(kostenHaus);
			haueser++;
			informiereBeobachter();
		}
	}

	public void hausVerkaufen()
	{
		if (haueser > 0 && eigentuemer.isPresent())
		{
			for (RessourcenDatensatz ressourcenDatensatz : kostenHaus)
			{
				if (ressourcenDatensatz.getRessource() != Ressource.GELD)
				{
					eigentuemer.get().einzahlen(ressourcenDatensatz);
				}
			}
			hausZerstoeren();
		}
	}

	public void hausZerstoeren()
	{
		if (haueser > 0)
		{
			haueser--;
			informiereBeobachter();
		}
	}

	public boolean isHausbauMoeglich()
	{
		return eigentuemer.isPresent() && eigentuemer.get().kannBezahlen(kostenHaus) && haueser < miete.length - 1;
	}

	public void zurueckgeben()
	{
		eigentuemer = Optional.empty();
	}
}
