package de.dhpoly.spiel.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.fenster.view.Fenster;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.view.SpielerUI;
import de.dhpoly.spielfeld.view.SpielfeldUI;
import de.dhpoly.wuerfel.control.WuerfelImpl;
import de.dhpoly.wuerfel.view.WuerfelUI;

public class SpielUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	public SpielUI(Spiel spiel)
	{
		this.setLayout(new BorderLayout(10, 10));
		this.add(new SpielfeldUI(spiel.getFelder()));
		this.setBorder(new LineBorder(Fenster.getDesignfarbe(), 10));

		JPanel pnlKassen = new JPanel();
		pnlKassen.setLayout(new GridLayout(spiel.getSpieler().size(), 1));

		for (Spieler spieler : spiel.getSpieler())
		{
			pnlKassen.add(new SpielerUI(spieler, spiel));
		}

		this.add(pnlKassen, BorderLayout.EAST);

		JPanel pnlWuerfel = new JPanel(new GridLayout(1, 10));
		pnlWuerfel.add(new WuerfelUI((WuerfelImpl) spiel.getWuerfel(), 1));
		pnlWuerfel.add(new WuerfelUI((WuerfelImpl) spiel.getWuerfel(), 2));
		pnlWuerfel.setBorder(new LineBorder(Fenster.getDesignfarbe(), 10));
		pnlWuerfel.setBackground(Fenster.getDesignfarbe());

		JButton butWeiter = Fenster.getButtonUeberschrift("Weiter");
		butWeiter.addActionListener(e -> {
			spiel.naechsterSpieler();
			spiel.ruecke();
		});

		pnlWuerfel.add(butWeiter);
		this.add(pnlWuerfel, BorderLayout.SOUTH);
	}
}
