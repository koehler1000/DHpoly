package de.dhpoly.spiel.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.spiel.Spiel;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.view.SpielerUI;
import de.dhpoly.spielfeld.view.SpielfeldUI;
import de.dhpoly.wuerfel.control.WuerfelImpl;
import de.dhpoly.wuerfel.view.WuerfelUI;

public class SpielUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	private static Color HINTERGRUNDFARBE = Color.YELLOW;
	private static Color HINTERGRUNDFARBE_SPIELFELD = Color.YELLOW;

	public SpielUI(Spiel spiel)
	{

		this.setLayout(new BorderLayout());
		this.add(new SpielfeldUI(spiel.getFelder()));

		JPanel pnlKassen = new JPanel();
		pnlKassen.setLayout(new GridLayout(spiel.getSpieler().size(), 1));

		for (Spieler spieler : spiel.getSpieler())
		{
			pnlKassen.add(new SpielerUI(spieler, spiel));
		}

		this.add(pnlKassen, BorderLayout.EAST);

		JPanel pnlWuerfel = new JPanel(new GridLayout(1, 10));
		pnlWuerfel.add(new WuerfelUI((WuerfelImpl) spiel.getWuerfel(), 1, HINTERGRUNDFARBE));
		pnlWuerfel.add(new WuerfelUI((WuerfelImpl) spiel.getWuerfel(), 2, HINTERGRUNDFARBE));
		pnlWuerfel.setBorder(new LineBorder(HINTERGRUNDFARBE, 10));
		pnlWuerfel.setBackground(HINTERGRUNDFARBE);

		JButton butWeiter = new JButton("Weiter");
		butWeiter.addActionListener(e -> {
			spiel.naechsterSpieler();
			spiel.ruecke();
		});

		pnlWuerfel.add(butWeiter);
		this.add(pnlWuerfel, BorderLayout.SOUTH);
	}
}
