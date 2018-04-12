package de.dhpoly.handel.view;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.Felderverwaltung;
import de.dhpoly.feld.control.FelderverwaltungImpl;
import de.dhpoly.feld.control.StrasseTest;
import de.dhpoly.handel.model.Transaktion;
import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.control.RessourcenDatensatzImpl;
import de.dhpoly.ressource.model.Ressource;
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
		felder.add(StrasseTest.getDefaultStrasse(verwaltung, s1));
		felder.add(StrasseTest.getDefaultStrasse(verwaltung, s2));
		felder.add(StrasseTest.getDefaultStrasse(verwaltung, s1));
		felder.add(StrasseTest.getDefaultStrasse(verwaltung, s1));
		verwaltung.setFelder(felder);

		List<RessourcenDatensatz> datensaetze = new ArrayList<>();
		datensaetze.add(new RessourcenDatensatzImpl(Ressource.GELD, 10000));
		datensaetze.add(new RessourcenDatensatzImpl(Ressource.HOLZ, 10));
		datensaetze.add(new RessourcenDatensatzImpl(Ressource.STEIN, 50));

		s1.einzahlen(datensaetze);
		s2.einzahlen(datensaetze);

		Transaktion transaktion = new Transaktion(s1, s2);

		Spielansicht.zeige(new HandelUI(transaktion, Spielansicht.getSpielfeldAnsicht()));
	}
}
