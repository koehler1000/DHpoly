package de.dhpoly.spieler.view;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

import de.dhpoly.einstellungen.model.EinstellungenImpl;
import de.dhpoly.ressource.control.RessourcenDatensatzImpl;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spiel.control.SpielImpl;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImplTest;
import de.dhpoly.wuerfel.control.WuerfelImpl;

public class KontoUITest
{
	public static void main(String[] args)
	{
		Spieler spieler = SpielerImplTest.getDefaultSpieler("Hans", 50);
		Spieler spieler2 = SpielerImplTest.getDefaultSpieler(-50);

		JFrame frame = new JFrame("Test");
		frame.setLayout(new GridLayout(2, 1));

		SpielImpl spiel = new SpielImpl(new ArrayList<>(), new EinstellungenImpl(), new WuerfelImpl());
		frame.add(new SpielerUI(spieler, spiel));
		frame.add(new SpielerUI(spieler2, spiel));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 150);
		frame.setVisible(true);

		Thread thread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				for (int i = 50; i > -50; i--)
				{
					spieler.auszahlen(new RessourcenDatensatzImpl(Ressource.GELD, 1));
					spieler2.einzahlen(new RessourcenDatensatzImpl(Ressource.GELD, 5));
					try
					{
						Thread.sleep(100);
					}
					catch (InterruptedException ex)
					{
						ex.printStackTrace();
					}
				}
			}
		});

		thread.start();
	}
}
