package de.dhpoly.oberflaeche.view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.oberflaeche.ElementFactory;

public class OberflaecheUI
{
	private JFrame frame = new JFrame("");
	private JTabbedPane pnlInhalt;

	private JTabbedPane pnlMitte;

	public OberflaecheUI(Bilderverwalter bilderverwalter, String spielername)
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setTitle("DHPoly - " + spielername);

		frame.setIconImage(bilderverwalter.getBild(Bilderverwalter.LOGO).getImage());
		frame.setLayout(new BorderLayout());

		pnlInhalt = ElementFactory.getTabbedPane();
		pnlMitte = ElementFactory.getTabbedPane();

		frame.add(pnlMitte);

		frame.setVisible(true);
	}

	public void zeigeAufRand(String beschreibung, Component component)
	{
		pnlInhalt.removeAll();
		pnlInhalt.addTab(beschreibung, component);
	}

	public void zeigeKomplettesFenster(Component component)
	{
		pnlMitte.addTab("", component);
	}

	public void leereRand()
	{
		pnlInhalt.removeAll();
	}

	public Component getRand()
	{
		return pnlInhalt;
	}
}
