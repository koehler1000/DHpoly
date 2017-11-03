package de.dhpoly.wuerfel.view;

import javax.swing.JFrame;

public class WuerfelAnzeigeTest
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Würfel");
		frame.setSize(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new WuerfelUI(1, 5));
		frame.setVisible(true);
	}
}
