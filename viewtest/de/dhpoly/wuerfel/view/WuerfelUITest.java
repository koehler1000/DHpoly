package de.dhpoly.wuerfel.view;

import de.dhpoly.pause.Pause;
import de.dhpoly.spiel.view.SpielUIVerwalter;
import de.dhpoly.utils.Spielansicht;
import de.dhpoly.wuerfel.control.WuerfelImpl;

public class WuerfelUITest // NOSONAR
{
	public static void main(String[] args)
	{
		WuerfelImpl wuerfel = new WuerfelImpl();

		Thread t1 = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				while (true)
				{
					wuerfel.wuerfeln();
					Pause.pause(1000, new SpielUIVerwalter());
				}
			}
		});
		t1.start();

		Spielansicht.zeige(new WuerfelUI(wuerfel, 1, Spielansicht.getSpielfeldAnsicht()));
	}
}
