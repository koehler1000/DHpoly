package de.dhpoly.spieler.view;

import java.awt.GridLayout;

import javax.swing.JFrame;

import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImplTest;

public class KontoTest
{
	public static void main(String[] args)
	{
		Spieler spieler = SpielerImplTest.getDefaultSpieler(50);
		Spieler spieler2 = SpielerImplTest.getDefaultSpieler(-50);

		JFrame frame = new JFrame("Test");
		frame.setLayout(new GridLayout(2, 1));

		frame.add(new Konto(spieler));
		frame.add(new Konto(spieler2));
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
					spieler.auszahlen(1);
					spieler2.einzahlen(1);
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
