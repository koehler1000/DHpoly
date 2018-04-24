package de.dhpoly.oberflaeche.view;

import java.awt.BorderLayout;
import java.awt.Component;

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

	public void zeigeComponente(Component componente, String titel)
	{
		spielansichten.addTab(titel, componente);
	}

	public void zeigeTab(String name)
	{
		int tabIdx = spielansichten.indexOfTab(name);
		if (tabIdx >= 0)
		{
			spielansichten.setSelectedIndex(tabIdx);
		}
	}

	public void loescheKomponente(Component komponente)
	{
		spielansichten.remove(komponente);
	}

	public void verstecken()
	{
		frame.setVisible(false);
	}
}
