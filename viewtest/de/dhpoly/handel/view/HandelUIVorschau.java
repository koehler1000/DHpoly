package de.dhpoly.handel.view;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.feld.control.FeldStrasseTest;
import de.dhpoly.feld.control.FelderverwaltungImpl;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.ressource.model.RessourcenDatensatz;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImplTest;
import de.dhpoly.utils.Spielansicht;

public class HandelUIVorschau
{
	public static void main(String[] args)
	{
		Spieler s1 = SpielerImplTest.getDefaultSpieler("s1", 0);
		Spieler s2 = SpielerImplTest.getDefaultSpieler("s2", 0);

		Felderverwaltung verwaltung = new FelderverwaltungImpl();
		List<Feld> felder = new ArrayList<>();
		felder.add(FeldStrasseTest.getDefaultStrasse(verwaltung, s1));
		felder.add(FeldStrasseTest.getDefaultStrasse(verwaltung, s2));
		felder.add(FeldStrasseTest.getDefaultStrasse(verwaltung, s1));
		felder.add(FeldStrasseTest.getDefaultStrasse(verwaltung, s1));
		verwaltung.setFelder(felder);

		List<RessourcenDatensatz> datensaetze = new ArrayList<>();
		datensaetze.add(new RessourcenDatensatz(Ressource.GELD, 10000));
		datensaetze.add(new RessourcenDatensatz(Ressource.HOLZ, 10));
		datensaetze.add(new RessourcenDatensatz(Ressource.STEIN, 50));

		s1.einzahlen(datensaetze);
		s2.einzahlen(datensaetze);

		Transaktion transaktion = new Transaktion(s1.getDaten(), s2.getDaten());

		Spielansicht.zeige(new HandelUI(transaktion, Spielansicht.getSpielfeldAnsicht()));
	}
}
