package de.dhpoly.feld.control;

import java.util.List;
import java.util.Optional;

import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.feld.model.Strasse;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.Spieler;

public class FeldStrasse extends FeldImpl
{
	private Strasse strasse;

	private Felderverwaltung strassenverwaltung;

	public FeldStrasse(Felderverwaltung strassenverwaltung, int kaufpreis, int[] miete,
			List<RessourcenDatensatz> kostenHaus, int gruppe, String name)
	{
		super(name);
		this.strassenverwaltung = strassenverwaltung;

		strasse = new Strasse();
		strasse.setMiete(miete);
		strasse.setKostenHaus(kostenHaus);
		strasse.setGruppe(gruppe);
		strasse.setName(name);
		strasse.setKaufpreis(kaufpreis);
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
		return strasse.getEigentuemer().isPresent();
	}

	public void kaufe(Spieler potentiellerKaeufer)
	{
		kaufe(potentiellerKaeufer, strasse.getKaufpreis());
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
		strasse.getEigentuemer()
				.ifPresent(besitzer -> einzahlenFallsKeineHypothek(zahlender, besitzer, getMietDatensatz(wetter)));
	}

	private void einzahlenFallsKeineHypothek(Spieler zahlender, Spieler besitzer, RessourcenDatensatz mietDatensatz)
	{
		if (!strasse.isHypothek())
		{
			zahlender.auszahlen(mietDatensatz);
			besitzer.einzahlen(mietDatensatz);
		}
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
		if (strasse.getEigentuemer().isPresent())
		{
			if (strassenverwaltung.isNutzerBesitzerAllerStrassen(strasse.getGruppe(), strasse.getEigentuemer().get())
					&& strasse.getHaueser() == 0)
			{
				return strasse.getMiete()[0] * 2;
			}
			else
			{
				return strasse.getMiete()[strasse.getHaueser()];
			}
		}
		return 0;
	}

	public void setEigentuemer(Spieler anbietender)
	{
		strasse.setEigentuemer(Optional.ofNullable(anbietender));
		informiereBeobachter();
	}

	public Optional<Spieler> getEigentuemer()
	{
		return strasse.getEigentuemer();
	}

	public int getHaeuser()
	{
		return strasse.getHaueser();
	}

	public boolean isHypothek()
	{
		return strasse.isHypothek();
	}

	public int[] getMiete()
	{
		return strasse.getMiete();
	}

	public int getGruppe()
	{
		return strasse.getGruppe();
	}

	public String getName()
	{
		return strasse.getName();
	}

	public int getKaufpreis()
	{
		return strasse.getKaufpreis();
	}

	public List<RessourcenDatensatz> getKostenHaus()
	{
		return strasse.getKostenHaus();
	}

	@Override
	public boolean gehoertSpieler(Spieler spieler)
	{
		return strasse.getEigentuemer().isPresent() && strasse.getEigentuemer().get() == spieler;
	}

	public void hausBauen()
	{
		if (strasse.getHaueser() < strasse.getMiete().length - 1 && strasse.getEigentuemer().isPresent()
				&& strasse.getEigentuemer().get().kannBezahlen(strasse.getKostenHaus()))
		{
			strasse.getEigentuemer().get().auszahlen(strasse.getKostenHaus());
			strasse.setHaueser(strasse.getHaueser() + 1);
			informiereBeobachter();
		}
	}

	public void hausVerkaufen()
	{
		if (strasse.getHaueser() > 0 && strasse.getEigentuemer().isPresent())
		{
			for (RessourcenDatensatz ressourcenDatensatz : strasse.getKostenHaus())
			{
				if (ressourcenDatensatz.getRessource() != Ressource.GELD)
				{
					strasse.getEigentuemer().get().einzahlen(ressourcenDatensatz);
				}
			}
			hausZerstoeren();
		}
	}

	public void hausZerstoeren()
	{
		if (strasse.getHaueser() > 0)
		{
			strasse.setHaueser(strasse.getHaueser() - 1);
			informiereBeobachter();
		}
	}

	public boolean isHausbauMoeglich()
	{
		return strasse.getEigentuemer().isPresent()
				&& strasse.getEigentuemer().get().kannBezahlen(strasse.getKostenHaus())
				&& strasse.getHaueser() < strasse.getMiete().length - 1;
	}

	public void zurueckgeben()
	{
		strasse.setEigentuemer(Optional.empty());
	}
}
