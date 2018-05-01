package de.dhpoly.feld.control;

import java.util.List;
import java.util.Optional;

import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.feld.model.Strasse;
import de.dhpoly.feld.model.StrasseKaufen;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.model.SpielerDaten;

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
			spieler.zeigeDatenobjekt(new StrasseKaufen(strasse));
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
		if (isVerkauft())
		{
			return berechneMiete();
		}
		return 0;
	}

	private int berechneMiete()
	{
		if (isDoppelteMiete())
		{
			return strasse.getMiete()[0] * 2;
		}
		else
		{
			return strasse.getMiete()[strasse.getHaueser()];
		}
	}

	private boolean isDoppelteMiete()
	{
		return strassenverwaltung.isNutzerBesitzerAllerStrassen(strasse.getGruppe(), strasse.getEigentuemer())
				&& strasse.getHaueser() == 0;
	}

	public void setEigentuemer(Spieler anbietender)
	{
		strasse.setEigentuemer(Optional.ofNullable(anbietender));
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
		return strasse.isEigentuemer(spieler);
	}

	public void hausBauen()
	{
		if (!strasse.isAlleHaeuserGebaut())
		{
			strasse.getEigentuemer().ifPresent(besitzer -> {
				if (besitzer.kannBezahlen(strasse.getKostenHaus()))
				{
					strasse.getEigentuemer().get().auszahlen(strasse.getKostenHaus());
					strasse.setHaueser(strasse.getHaueser() + 1);
				}
			});
		}
	}

	public void hausVerkaufen()
	{
		if (strasse.haeuserGebaut())
		{
			hausZerstoeren();
			strasse.getKostenHaus().stream().filter(e -> e.getRessource() != Ressource.GELD)
					.forEach(e -> strasse.getEigentuemer().ifPresent(besitzer -> besitzer.einzahlen(e)));
		}
	}

	public void hausZerstoeren()
	{
		if (strasse.getHaueser() > 0)
		{
			strasse.setHaueser(strasse.getHaueser() - 1);
		}
	}

	public void zurueckgeben()
	{
		strasse.setEigentuemer(Optional.empty());
	}

	public Strasse getStrasse()
	{
		return strasse;
	}

	public boolean kannBebautWerden()
	{
		return !strasse.isAlleHaeuserGebaut()
				&& getEigentuemer().filter(e -> e.kannBezahlen(getKostenHaus())).isPresent();
	}

	@Override
	public boolean gehoertSpieler(SpielerDaten spielerDaten)
	{
		return (strasse.getEigentuemer().isPresent() && strasse.getEigentuemer().get().getDaten() == spielerDaten);
	}
}
