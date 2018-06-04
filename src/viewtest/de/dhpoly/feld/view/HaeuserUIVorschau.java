package de.dhpoly.feld.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.control.SpielerImplTest;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.utils.Spielansicht;

public class HaeuserUIVorschau
{
	public static void main(String[] args)
	{
		StrasseDaten strasse = new StrasseDaten();
		StrasseDaten strasse2 = new StrasseDaten();

		List<StrasseDaten> felder = new ArrayList<>();
		felder.add(strasse);
		felder.add(strasse2);

		List<RessourcenDatensatz> ressourcen = new ArrayList<>();
		ressourcen.add(new RessourcenDatensatz(Ressource.GELD, 100000));
		ressourcen.add(new RessourcenDatensatz(Ressource.STEIN, 100000));
		ressourcen.add(new RessourcenDatensatz(Ressource.HOLZ, 100000));

		Spieler spieler = SpielerImplTest.getDefaultSpieler();
		spieler.einzahlen(ressourcen);

		strasse.setEigentuemer(Optional.ofNullable(spieler));

		Spielansicht.zeige(new HaeuserUI(felder, Spielansicht.getSpielfeldAnsicht()));
	}
}
