package de.dhpoly.spieler.view;

import java.awt.GridLayout;
import java.util.Optional;

import javax.swing.JFrame;

import de.dhpoly.pause.Pause;
import de.dhpoly.ressource.control.RessourcenDatensatzImpl;
import de.dhpoly.ressource.model.Ressource;
import de.dhpoly.spiel.view.SpielUIVerwalter;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImplTest;

public class KontoUITest // NOSONAR
{
	public static void main(String[] args)
	{
		Spieler spieler = SpielerImplTest.getDefaultSpieler("Hans", 50);
		Spieler spieler2 = SpielerImplTest.getDefaultSpieler(-50);

		JFrame frame = new JFrame("Test");
		frame.setLayout(new GridLayout(2, 1));

		frame.add(new SpielerUI(spieler, Optional.ofNullable(null)));
		frame.add(new SpielerUI(spieler2, Optional.ofNullable(null)));
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
					Pause.pause(100, new SpielUIVerwalter());
				}
			}
		});

		thread.start();
	}
}
