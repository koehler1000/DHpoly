package de.dhpoly.oberflaeche.view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.spielersteller.view.SpielerstellerUI;

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
		spielansichten.setSelectedIndex(spielansichten.indexOfTab(name));
	}

	public void loescheKomponente(SpielerstellerUI komponente)
	{
		spielansichten.remove(komponente);
	}
}
