package de.dhpoly.spieler.view;

import java.awt.GridLayout;

import javax.swing.JFrame;

import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.control.SpielerImplTest;

public class KontoUITest
{
	public static void main(String[] args)
	{
		Spieler spieler = SpielerImplTest.getDefaultSpieler("Hans", 50);
		Spieler spieler2 = SpielerImplTest.getDefaultSpieler(-50);

		JFrame frame = new JFrame("Test");
		frame.setLayout(new GridLayout(2, 1));

		frame.add(new KontoUI(spieler));
		frame.add(new KontoUI(spieler2));
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
					spieler2.einzahlen(5);
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
