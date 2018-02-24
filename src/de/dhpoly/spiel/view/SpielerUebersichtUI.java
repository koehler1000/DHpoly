package de.dhpoly.spiel.view;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import de.dhpoly.fenster.view.Fenster;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.view.SpielerUI;
import observerpattern.Beobachter;

public class SpielerUebersichtUI extends JTabbedPane implements Beobachter
{
	private static final long serialVersionUID = 1L;
	private transient Spiel spiel;

	public SpielerUebersichtUI(Spiel spiel)
	{
		this.spiel = spiel;
		update();

		spiel.addBeobachter(this);
	}

	@Override
	public void update()
	{
		this.removeAll();

		if (!spiel.getSpieler().isEmpty())
		{
			JPanel pnlAlleSpieler = new JPanel(new GridLayout(spiel.getSpieler().size(), 1, 10, 10));
			pnlAlleSpieler.setBackground(Fenster.getDesignfarbe());

			this.addTab("Alle Spieler", pnlAlleSpieler);
			for (Spieler spieler : spiel.getSpieler())
			{
				pnlAlleSpieler.add(new SpielerUI(spieler, spiel));
				this.addTab(spieler.getName(), new SpielerUI(spieler, spiel));
			}
		}
	}
}
