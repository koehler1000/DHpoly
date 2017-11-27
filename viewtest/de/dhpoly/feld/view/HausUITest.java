package de.dhpoly.feld.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.feld.control.StrasseTest;
import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.control.RessourcenDatensatzImpl;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImplTest;

public class HausUITest
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("HausUITest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Felderverwaltung verwaltung = new Felderverwaltung()
		{
			@Override
			public void setFelder(List<Feld> felder)
			{}

			@Override
			public boolean isNutzerBesitzerAllerStrassen(int strassengruppe, Spieler eigentuemer)
			{
				return false;
			}

			@Override
			public boolean isEigentuemer(Feld feld, Spieler moeglicherEigentuemer)
			{
				return true;
			}

			@Override
			public List<Feld> getFelder(Spieler spieler)
			{
				return null;
			}
		};

		Strasse strasse = StrasseTest.getDefaultStrasse(new int[] { 1, 10, 20, 50, 75 }, verwaltung);
		frame.add(new HausUI(strasse));

		List<RessourcenDatensatz> ressourcen = new ArrayList<>();
		ressourcen.add(new RessourcenDatensatzImpl(Ressource.GELD, 100000));
		ressourcen.add(new RessourcenDatensatzImpl(Ressource.STEIN, 100000));
		ressourcen.add(new RessourcenDatensatzImpl(Ressource.HOLZ, 100000));

		Spieler spieler = SpielerImplTest.getDefaultSpieler();
		spieler.einzahlen(ressourcen);

		strasse.kaufe(spieler);

		frame.setSize(1000, 1000);
		frame.setVisible(true);
	}
}
