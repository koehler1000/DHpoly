package de.dhpoly.spieler.model;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.view.SpielerUI;

public class Spieler extends Datenobjekt
{
	private static final long serialVersionUID = 1L;

	private String name;
	private List<RessourcenDatensatz> kasse = new ArrayList<>();
	private int spielerNr;
	private SpielerStatus status;
	private boolean anDerReihe = false;
	private int feldNr;
	private List<StrasseDaten> strassen = new ArrayList<>();

	public Spieler(String name)
	{
		super();
		this.name = name;
		this.status = SpielerStatus.WARTET;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public String getTitel()
	{
		return "Spielerdaten";
	}

	@Override
	public Class<? extends Oberflaeche> getClassUI()
	{
		return SpielerUI.class;
	}

	public int getRessourcenWert(Ressource ressource)
	{
		int wert = 0;
		for (RessourcenDatensatz ressourcenDatensatz : kasse)
		{
			if (ressourcenDatensatz.getRessource() == ressource)
			{
				wert += ressourcenDatensatz.getAnzahl();
			}
		}
		return wert;
	}

	public int getSpielerNr()
	{
		return spielerNr;
	}

	public void setSpielerNr(int spielerNr)
	{
		this.spielerNr = spielerNr;
	}

	public void einzahlen(List<RessourcenDatensatz> datensaetze)
	{
		for (RessourcenDatensatz ressourcenDatensatz : datensaetze)
		{
			einzahlen(ressourcenDatensatz);
		}
	}

	public void auszahlen(List<RessourcenDatensatz> datensaetze)
	{
		for (RessourcenDatensatz ressourcenDatensatz : datensaetze)
		{
			auszahlen(ressourcenDatensatz);
		}
	}

	public void einzahlen(RessourcenDatensatz datensatz)
	{
		kasse.add(datensatz);
	}

	public void auszahlen(RessourcenDatensatz datensatz)
	{
		RessourcenDatensatz satz = new RessourcenDatensatz(datensatz.getRessource(), 0 - datensatz.getAnzahl(),
				datensatz.getBeschreibung());
		kasse.add(satz);
	}

	public final List<RessourcenDatensatz> getKasse()
	{
		return kasse;
	}

	public SpielerStatus getStatus()
	{
		return status;
	}

	public boolean isAnDerReihe()
	{
		return anDerReihe;
	}

	public void setAktuellerSpieler(boolean isAktuell)
	{
		anDerReihe = isAktuell;
	}

	public void setSpielerStatus(SpielerStatus status)
	{
		this.status = status;
	}

	public boolean kannBezahlen(List<RessourcenDatensatz> kostenHaus)
	{
		for (RessourcenDatensatz ressourcenDatensatz : kostenHaus)
		{
			if (getRessourcenWert(ressourcenDatensatz.getRessource()) < ressourcenDatensatz.getAnzahl())
			{
				return false;
			}
		}
		return true;
	}

	public int getFeldNr()
	{
		return feldNr;
	}

	public void setFeldNr(int feldNr)
	{
		this.feldNr = feldNr;
	}

	public List<StrasseDaten> getStrassen()
	{
		return strassen;
	}

	public void leereStrassen()
	{
		strassen = new ArrayList<>();
	}

	public void addStrasse(StrasseDaten strasse)
	{
		strassen.add(strasse);
	}

	public void removeStrasse(StrasseDaten strasse)
	{
		strassen.remove(strasse);
	}

	public boolean hatAlleStrassenDerGruppe(int i)
	{
		List<StrasseDaten> strassenDerGruppe = new ArrayList<>();
		strassen.stream().filter(e -> (e.getGruppe() == i)).forEach(strassenDerGruppe::add);

		if (strassenDerGruppe.isEmpty())
		{
			return false;
		}
		return strassenDerGruppe.get(0).getStrassenAnzahlInGruppe() == strassenDerGruppe.size();
	}
}
