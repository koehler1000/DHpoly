package de.dhpoly.einstellungen;

import java.util.List;

import de.dhpoly.ressource.RessourcenDatensatz;

public interface Einstellungen
{
	void setKostenHausHolz(int kostenHausHolz);

	int getKostenHausHolz();

	void setKostenHausGeld(int kostenHausGeld);

	int getKostenHausGeld();

	void setRessourcenErtrag(int ressourcenErtrag);

	int getRessourcenErtrag();

	void setStartguthaben(int startguthaben);

	int getStartguthaben();

	void setBetragPassierenLos(int betragPassierenLos);

	int getBetragPassierenLos();

	void setKostenHausStein(int kostenHausStein);

	int getKostenHausStein();

	int getBetragBetretenLos();

	void setBetragBetretenLos(int betragBetretenLos);

	List<RessourcenDatensatz> getHauskosten(int seite);

	List<RessourcenDatensatz> getSpielerStartVorraete();
}
