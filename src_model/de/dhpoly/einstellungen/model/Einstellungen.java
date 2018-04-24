package de.dhpoly.einstellungen.model;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;

public class Einstellungen
{
	private int startguthaben = 1000;

	private int betragBetretenLos = 100;
	private int betragPassierenLos = 100;

	// Kosten je auf Seite 1 -> Seite 2 = Kosten von Seite 1 * 2
	private int kostenHausStein = 10;
	private int kostenHausGeld = 100;
	private int kostenHausHolz = 10;

	private int ressourcenErtrag = 10;

	public int getBetragBetretenLos()
	{
		return betragBetretenLos;
	}

	public void setBetragBetretenLos(int betragBetretenLos)
	{
		this.betragBetretenLos = betragBetretenLos;
	}

	public int getKostenHausStein()
	{
		return kostenHausStein;
	}

	public void setKostenHausStein(int kostenHausStein)
	{
		this.kostenHausStein = kostenHausStein;
	}

	public int getBetragPassierenLos()
	{
		return betragPassierenLos;
	}

	public void setBetragPassierenLos(int betragPassierenLos)
	{
		this.betragPassierenLos = betragPassierenLos;
	}

	public int getStartguthaben()
	{
		return startguthaben;
	}

	public void setStartguthaben(int startguthaben)
	{
		this.startguthaben = startguthaben;
	}

	public int getRessourcenErtrag()
	{
		return ressourcenErtrag;
	}

	public void setRessourcenErtrag(int ressourcenErtrag)
	{
		this.ressourcenErtrag = ressourcenErtrag;
	}

	public int getKostenHausGeld()
	{
		return kostenHausGeld;
	}

	public void setKostenHausGeld(int kostenHausGeld)
	{
		this.kostenHausGeld = kostenHausGeld;
	}

	public int getKostenHausHolz()
	{
		return kostenHausHolz;
	}

	public void setKostenHausHolz(int kostenHausHolz)
	{
		this.kostenHausHolz = kostenHausHolz;
	}

	public List<RessourcenDatensatz> getHauskosten(int seite)
	{
		List<RessourcenDatensatz> ressourcenDatensaetze = new ArrayList<>();
		ressourcenDatensaetze.add(new RessourcenDatensatz(Ressource.GELD, kostenHausGeld * seite, "Hauskosten"));
		ressourcenDatensaetze.add(new RessourcenDatensatz(Ressource.HOLZ, kostenHausHolz * seite, "Hausbau"));
		ressourcenDatensaetze.add(new RessourcenDatensatz(Ressource.STEIN, kostenHausStein * seite, "Hausbau"));
		return ressourcenDatensaetze;
	}

	public List<RessourcenDatensatz> getSpielerStartVorraete()
	{
		List<RessourcenDatensatz> ressourcenDatensaetze = new ArrayList<>();
		ressourcenDatensaetze.add(new RessourcenDatensatz(Ressource.GELD, startguthaben, "Startguthaben"));
		return ressourcenDatensaetze;
	}
}
