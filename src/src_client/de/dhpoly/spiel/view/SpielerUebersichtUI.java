package de.dhpoly.spiel.view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.SpielUI;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.view.SpielerUI;

public class SpielerUebersichtUI extends JTabbedPane
{
	private static final long serialVersionUID = 1L;
	private transient Spiel spiel;
	private transient SpielUI ansicht;

	private transient List<Spieler> cacheSpieler = new ArrayList<>();

	public SpielerUebersichtUI(Spiel spiel, SpielUI ansicht)
	{
		this.spiel = spiel;
		this.ansicht = ansicht;
		update();
	}

	public void update()
	{
		if (cacheSpieler.size() != spiel.getSpieler().size())
		{
			// Neuzeichnen nur, wenn Spieler sich verändern.

			cacheSpieler = new ArrayList<>(spiel.getSpieler());

			this.removeAll();

			if (!spiel.getSpieler().isEmpty())
			{
				JPanel pnlAlleSpieler = ElementFactory.erzeugePanel();
				pnlAlleSpieler.setLayout(new GridLayout(spiel.getSpieler().size(), 1, 10, 10));

				this.addTab("Alle Spieler", ElementFactory.erzeugeScrollPanel(pnlAlleSpieler));
				for (Spieler spieler : spiel.getSpieler())
				{
					pnlAlleSpieler.add(new SpielerUI(spieler, ansicht));
					this.addTab(spieler.getName(), new SpielerUI(spieler, ansicht));
				}
			}
		}
	}
}
