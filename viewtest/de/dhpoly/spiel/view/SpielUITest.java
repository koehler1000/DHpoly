package de.dhpoly.spiel.view;

import javax.swing.JFrame;

import de.dhpoly.einstellungen.Einstellungen;
import de.dhpoly.einstellungen.control.EinstellungenImpl;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spiel.control.SpielImpl;
import de.dhpoly.spieler.control.SpielerImpl;
import de.dhpoly.spielfeld.control.SpielfeldImpl;

public class SpielUITest
{

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("DHPoly");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Einstellungen einstellungen = new EinstellungenImpl();
		Spiel spiel = new SpielImpl(new SpielfeldImpl().getStandardSpielfeld(), einstellungen);

		spiel.fuegeSpielerHinzu(new SpielerImpl("Rico", einstellungen.getStartguthaben(), spiel));
		spiel.fuegeSpielerHinzu(new SpielerImpl("Sven", einstellungen.getStartguthaben(), spiel));
		spiel.fuegeSpielerHinzu(new SpielerImpl("Alex", einstellungen.getStartguthaben(), spiel));

		frame.add(new SpielUI(spiel));
		frame.setSize(1000, 1000);

		frame.setVisible(true);

		Thread thread = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				while (true)
				{
					spiel.ruecke(spiel.getAktuellerSpieler(), 2);
					try
					{
						Thread.sleep(1000);
					}
					catch (InterruptedException ex)
					{
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
				}
			}
		});

		thread.start();
	}
}
