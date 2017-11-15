package de.dhpoly.feld.view;

import java.awt.GridLayout;

import javax.swing.JFrame;

import de.dhpoly.feld.control.Strasse;
import de.dhpoly.feld.control.StrasseTest;
import de.dhpoly.spieler.control.SpielerImplTest;

public class StrasseUITest
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Badstraﬂe wird in 3 Sekunden gekauft");
		frame.setLayout(new GridLayout(1, 2));

		Strasse strasse = StrasseTest.getDefaultStrasse("Badstraﬂe", 100);

		frame.add(new StrasseUI(strasse));
		frame.add(new StrasseUI(StrasseTest.getDefaultStrasse("Schlossallee", 1000)));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 550);
		frame.setVisible(true);

		Thread t1 = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					Thread.sleep(3000);
				}
				catch (InterruptedException ex)
				{
					ex.printStackTrace();
				}
				if (strasse.isKaufbar())
				{
					strasse.kaufe(SpielerImplTest.getDefaultSpieler());
				}
			}
		});

		t1.start();
	}
}
