package de.dhpoly.einstellungen.control;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.control.RessourcenDatensatzImpl;
import de.dhpoly.ressource.model.Ressource;

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
		ressourcenDatensaetze.add(new RessourcenDatensatzImpl(Ressource.GELD, kostenHausGeld * seite));
		ressourcenDatensaetze.add(new RessourcenDatensatzImpl(Ressource.HOLZ, kostenHausHolz * seite));
		ressourcenDatensaetze.add(new RessourcenDatensatzImpl(Ressource.STEIN, kostenHausStein * seite));
		return ressourcenDatensaetze;
	}

	@Override
	public List<RessourcenDatensatz> getSpielerStartVorraete()
	{
		List<RessourcenDatensatz> ressourcenDatensaetze = new ArrayList<>();
		ressourcenDatensaetze.add(new RessourcenDatensatzImpl(Ressource.GELD, startguthaben));
		return ressourcenDatensaetze;
	}
}
