package de.dhpoly.wuerfel.view;

import javax.swing.JFrame;

import de.dhpoly.pause.Pause;
import de.dhpoly.spiel.view.SpielUIVerwalter;
import de.dhpoly.wuerfel.control.WuerfelImpl;

public class WuerfelUITest // NOSONAR
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Würfel");
		frame.setSize(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		WuerfelImpl wuerfel = new WuerfelImpl();

		frame.add(new WuerfelUI(wuerfel, 1));
		frame.setVisible(true);
		frame.pack();

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
	}
}
