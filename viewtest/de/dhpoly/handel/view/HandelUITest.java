package de.dhpoly.handel.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import de.dhpoly.ressource.RessourcenDatensatz;
import de.dhpoly.ressource.control.RessourcenDatensatzImpl;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImplTest;

public class HandelUITest
{
	public static void main(String[] args)
	{
		Spieler s1 = SpielerImplTest.getDefaultSpieler();
		Spieler s2 = SpielerImplTest.getDefaultSpieler();

		List<RessourcenDatensatz> datensaetze = new ArrayList<>();
		datensaetze.add(new RessourcenDatensatzImpl(Ressource.GELD, 10000));
		datensaetze.add(new RessourcenDatensatzImpl(Ressource.HOLZ, 10));
		datensaetze.add(new RessourcenDatensatzImpl(Ressource.STEIN, 50));

		s1.einzahlen(datensaetze);
		s2.einzahlen(datensaetze);

		JFrame frame = new JFrame("HandelUITest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new HandelUI(s1, s2));
		frame.setSize(1000, 1000);
		frame.setVisible(true);
	}

}
