package de.dhpoly.spiel.view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.view.SpielerUI;
import observerpattern.Beobachter;

public class SpielerUebersichtUI extends JTabbedPane implements Beobachter
{
	private static final long serialVersionUID = 1L;
	private transient Spiel spiel;
	private transient SpielfeldAnsicht ansicht;

	private transient List<Spieler> cacheSpieler = new ArrayList<>();

	public SpielerUebersichtUI(Spiel spiel, SpielfeldAnsicht ansicht)
	{
		this.spiel = spiel;
		this.ansicht = ansicht;
		update();

		spiel.addBeobachter(this);
	}

	@Override
	public void update()
	{
		if (cacheSpieler.size() != spiel.getSpieler().size())
		{
			// Neuzeichnen nur, wenn Spieler sich verändern.

			cacheSpieler.addAll(spiel.getSpieler());

			this.removeAll();

			if (!spiel.getSpieler().isEmpty())
			{
				JPanel pnlAlleSpieler = ElementFactory.erzeugePanel();
				pnlAlleSpieler.setLayout(new GridLayout(spiel.getSpieler().size(), 1, 10, 10));

				this.addTab("Alle Spieler", pnlAlleSpieler);
				for (Spieler spieler : spiel.getSpieler())
				{
					pnlAlleSpieler.add(new SpielerUI(spieler, ansicht));
					this.addTab(spieler.getName(), new SpielerUI(spieler, ansicht));
				}
			}
		}
	}
}
