package de.dhpoly.oberflaeche.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.oberflaeche.ElementFactory;

public class Fenster
{
	private JFrame frame = new JFrame("");
	private JTabbedPane spielansichten;

	public Fenster(Bilderverwalter bilderverwalter)
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setTitle("DHPoly");

		frame.setIconImage(bilderverwalter.getBild(Bilderverwalter.LOGO).getImage());
		frame.setLayout(new BorderLayout());

		spielansichten = ElementFactory.getTabbedPane();

		frame.add(spielansichten);

		frame.setVisible(true);
	}

	public void zeigeSpielansicht(SpielfeldAnsicht ansicht, String titel)
	{
		spielansichten.addTab(titel, ansicht);
	}
}
