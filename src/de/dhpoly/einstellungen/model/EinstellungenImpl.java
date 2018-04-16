package de.dhpoly.einstellungen.model;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;

public class EinstellungenImpl implements Einstellungen
{
	private int startguthaben = 1000;

	private int betragBetretenLos = 100;
	private int betragPassierenLos = 100;

	// Kosten je auf Seite 1 -> Seite 2 = Kosten von Seite 1 * 2
	private int kostenHausStein = 10;
	private int kostenHausGeld = 100;
	private int kostenHausHolz = 10;

	private int ressourcenErtrag;

	@Override
	public int getBetragBetretenLos()
	{
		return betragBetretenLos;
	}

	@Override
	public void setBetragBetretenLos(int betragBetretenLos)
	{
		this.betragBetretenLos = betragBetretenLos;
	}

	@Override
	public int getKostenHausStein()
	{
		return kostenHausStein;
	}

	@Override
	public void setKostenHausStein(int kostenHausStein)
	{
		this.kostenHausStein = kostenHausStein;
	}

	@Override
	public int getBetragPassierenLos()
	{
		return betragPassierenLos;
	}

	@Override
	public void setBetragPassierenLos(int betragPassierenLos)
	{
		this.betragPassierenLos = betragPassierenLos;
	}

	@Override
	public int getStartguthaben()
	{
		return startguthaben;
	}

	@Override
	public void setStartguthaben(int startguthaben)
	{
		this.startguthaben = startguthaben;
	}

	@Override
	public int getRessourcenErtrag()
	{
		return ressourcenErtrag;
	}

	@Override
	public void setRessourcenErtrag(int ressourcenErtrag)
	{
		this.ressourcenErtrag = ressourcenErtrag;
	}

	@Override
	public int getKostenHausGeld()
	{
		return kostenHausGeld;
	}

	@Override
	public void setKostenHausGeld(int kostenHausGeld)
	{
		this.kostenHausGeld = kostenHausGeld;
	}

	@Override
	public int getKostenHausHolz()
	{
		return kostenHausHolz;
	}

	@Override
	public void setKostenHausHolz(int kostenHausHolz)
	{
		this.kostenHausHolz = kostenHausHolz;
	}

	@Override
	public List<RessourcenDatensatz> getHauskosten(int seite)
	{
		List<RessourcenDatensatz> ressourcenDatensaetze = new ArrayList<>();
		ressourcenDatensaetze.add(new RessourcenDatensatz(Ressource.GELD, kostenHausGeld * seite, "Hauskosten"));
		ressourcenDatensaetze.add(new RessourcenDatensatz(Ressource.HOLZ, kostenHausHolz * seite, "Hausbau"));
		ressourcenDatensaetze.add(new RessourcenDatensatz(Ressource.STEIN, kostenHausStein * seite, "Hausbau"));
		return ressourcenDatensaetze;
	}

	@Override
	public List<RessourcenDatensatz> getSpielerStartVorraete()
	{
		List<RessourcenDatensatz> ressourcenDatensaetze = new ArrayList<>();
		ressourcenDatensaetze.add(new RessourcenDatensatz(Ressource.GELD, startguthaben, "Startguthaben"));
		return ressourcenDatensaetze;
	}
}
