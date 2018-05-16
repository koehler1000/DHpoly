package de.dhpoly.feld.control;

import java.util.List;
import java.util.Optional;

import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.feld.model.StrasseKaufen;
import de.dhpoly.karte.model.Wetter;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.model.Spieler;

public class FeldStrasse extends FeldImpl
{
	private StrasseDaten strasse;

	public FeldStrasse(int kaufpreis, int[] miete, List<RessourcenDatensatz> kostenHaus, int gruppe, String name)
	{
		super(name);

		strasse = new StrasseDaten();
		strasse.setMiete(miete);
		strasse.setKostenHaus(kostenHaus);
		strasse.setGruppe(gruppe);
		strasse.setName(name);
		strasse.setKaufpreis(kaufpreis);
	}

	public FeldStrasse(StrasseDaten aktuellesFeld)
	{
		super(aktuellesFeld.getName());
		this.strasse = aktuellesFeld;
	}

	@Override
	public void spielerBetrittFeld(Spieler spieler, int augensumme, Spiel spiel)
	{
		spielerBetrittFeld(spieler, spiel);
	}

	public void spielerBetrittFeld(Spieler spieler, Spiel spiel)
	{
		if (isVerkauft())
		{
			zahle(spieler, spiel.getWetter());
		}
		else
		{
			spiel.zeigeSpieler(spieler, new StrasseKaufen(strasse, spieler));
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
			potentiellerKaeufer.addStrasse(getStrasse());
		}
	}

	private void zahle(Spieler spielerDaten, Wetter wetter)
	{
		strasse.getEigentuemer()
				.ifPresent(besitzer -> einzahlenFallsKeineHypothek(spielerDaten, besitzer, getMietDatensatz(wetter)));
	}

	private void einzahlenFallsKeineHypothek(Spieler spielerDaten, Spieler besitzer, RessourcenDatensatz mietDatensatz)
	{
		if (!strasse.isHypothek())
		{
			spielerDaten.auszahlen(mietDatensatz);
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
			return strasse.getMiete()[strasse.getHaeuser()];
		}
	}

	private boolean isDoppelteMiete()
	{
		return getEigentuemer().filter(e -> e.hatAlleStrassenDerGruppe(getGruppe())).isPresent();
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
		return strasse.getHaeuser();
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

	public void zurueckgeben()
	{
		strasse.setEigentuemer(Optional.empty());
	}

	public StrasseDaten getStrasse()
	{
		return strasse;
	}

	public boolean kannBebautWerden(Spiel spiel)
	{
		if (strasse.isAlleHaeuserGebaut())
		{
			return false;
		}

		Optional<Spieler> spieler = strasse.getEigentuemer();
		return spieler.filter(e -> e.kannBezahlen(getKostenHaus())).isPresent();
	}
}
