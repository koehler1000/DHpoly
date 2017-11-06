package de.dhpoly.spiel.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.view.KontoUI;
import de.dhpoly.spielfeld.view.SpielfeldUI;
import de.dhpoly.wuerfel.view.WuerfelUI;

public class SpielUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	public SpielUI(Spiel spiel)
	{
		this.setLayout(new BorderLayout());
		this.add(new SpielfeldUI(spiel.getFelder()));

		JPanel pnlKassen = new JPanel();
		pnlKassen.setLayout(new GridLayout(spiel.getSpieler().size(),1));

		for (Spieler spieler : spiel.getSpieler())
		{
			pnlKassen.add(new KontoUI(spieler));
		}

		this.add(pnlKassen, BorderLayout.EAST);

		this.add(new WuerfelUI(2, 3), BorderLayout.SOUTH);
	}
}
