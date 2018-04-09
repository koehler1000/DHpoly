package de.dhpoly.spiel.view;

import java.awt.GridLayout;
import java.util.Optional;

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
	private Optional<SpielfeldAnsicht> ansicht;

	public SpielerUebersichtUI(Spiel spiel, Optional<SpielfeldAnsicht> ansicht)
	{
		this.spiel = spiel;
		this.ansicht = ansicht;
		update();

		spiel.addBeobachter(this);
	}

	@Override
	public void update()
	{
		this.removeAll();

		if (!spiel.getSpieler().isEmpty())
		{
			JPanel pnlAlleSpieler = ElementFactory.erzeugePanel();
			pnlAlleSpieler.setLayout(new GridLayout(spiel.getSpieler().size(), 1, 10, 10));

			this.addTab("Alle Spieler", pnlAlleSpieler);
			for (Spieler spieler : spiel.getSpieler())
			{
				pnlAlleSpieler.add(new SpielerUI(spieler, spiel, ansicht));
				this.addTab(spieler.getName(), new SpielerUI(spieler, spiel, ansicht));
			}
		}
	}
}
