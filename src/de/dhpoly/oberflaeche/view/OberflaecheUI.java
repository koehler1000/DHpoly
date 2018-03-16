package de.dhpoly.oberflaeche.view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.fenster.view.Fenster;

public class OberflaecheUI
{
	private JFrame frame = new JFrame("");
	private JTabbedPane pnlInhalt = new JTabbedPane();

	public OberflaecheUI(Bilderverwalter bilderverwalter)
	{
		frame.setTitle("");
		frame.setIconImage(bilderverwalter.getBild(Bilderverwalter.LOGO).getImage());
		frame.setLayout(new BorderLayout());

		pnlInhalt.setBackground(Fenster.getDesignfarbe());

		frame.add(new JLabel("Bitte warten..."));

		frame.setSize(500, 750);
		frame.setVisible(true);
	}

	public void zeigeAufRand(String beschreibung, Component component)
	{
		pnlInhalt.removeAll();
		pnlInhalt.addTab(beschreibung, component);
	}

	public void zeigeKomplettesFenster(Component component)
	{
		frame.add(component);
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
