package de.dhpoly.oberflaeche.view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.fenster.view.Fenster;

public class OberflaecheUI extends JFrame
{
	private JTabbedPane pnlInhalt = new JTabbedPane();

	public OberflaecheUI(Bilderverwalter bilderverwalter)
	{
		this.setTitle("");
		this.setIconImage(bilderverwalter.getBild(Bilderverwalter.LOGO).getImage());
		this.setLayout(new BorderLayout());

		pnlInhalt.setBackground(Fenster.getDesignfarbe());

		this.add(new JLabel("Bitte warten..."));

		// this.add(pnlInhalt, BorderLayout.WEST);

		this.setSize(500, 750);
		this.setVisible(true);
	}

	public void zeigeAufRand(String beschreibung, Component component)
	{
		pnlInhalt.removeAll();
		pnlInhalt.addTab(beschreibung, component);
	}

	public void zeigeKomplettesFenster(Component component)
	{
		this.add(component);
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
