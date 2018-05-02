package de.dhpoly.feld.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.feld.control.FeldStrasse;
import de.dhpoly.feld.control.FeldStrasseTest;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImplTest;
import de.dhpoly.spieler.model.SpielerDaten;
import de.dhpoly.utils.Spielansicht;

public class HaeuserUIVorschau
{
	public static void main(String[] args)
	{
		Felderverwaltung verwaltung = new Felderverwaltung()
		{
			@Override
			public void setFelder(List<Feld> felder)
			{
				throw new UnsupportedOperationException("Felder werden nicht gesetzt");
			}

			@Override
			public boolean isNutzerBesitzerAllerStrassen(int strassengruppe, SpielerDaten eigentuemer)
			{
				return false;
			}

			@Override
			public boolean isEigentuemer(Feld feld, SpielerDaten moeglicherEigentuemer)
			{
				return true;
			}

			@Override
			public List<Feld> getFelder(Spieler spieler)
			{
				throw new UnsupportedOperationException("Felder werden nicht gesetzt");
			}

			@Override
			public boolean isNutzerBesitzerAllerStrassen(int strassengruppe, Optional<SpielerDaten> optional)
			{
				// TODO Auto-generated method stub
				return false;
			}
		};

		FeldStrasse strasse = FeldStrasseTest.getDefaultStrasse(new int[] { 1, 10, 20, 50, 75 }, verwaltung);
		FeldStrasse strasse2 = FeldStrasseTest.getDefaultStrasse(new int[] { 1, 10, 20, 50, 75 }, verwaltung);

		List<Feld> felder = new ArrayList<>();
		felder.add(strasse);
		felder.add(strasse2);

		List<RessourcenDatensatz> ressourcen = new ArrayList<>();
		ressourcen.add(new RessourcenDatensatz(Ressource.GELD, 100000));
		ressourcen.add(new RessourcenDatensatz(Ressource.STEIN, 100000));
		ressourcen.add(new RessourcenDatensatz(Ressource.HOLZ, 100000));

		Spieler spieler = SpielerImplTest.getDefaultSpieler();
		spieler.einzahlen(ressourcen);

		strasse.kaufe(spieler);
		strasse2.kaufe(spieler);

		Spielansicht.zeige(new HaeuserUI(felder, Spielansicht.getSpielfeldAnsicht()));
	}
}
