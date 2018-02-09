package de.dhpoly.spiel.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import observerpattern.Beobachter;

public class SpielUI extends JPanel implements Beobachter
{
	private static final long serialVersionUID = 1L;

	public SpielUI(Spiel spiel)
	{
		Spiel.leerePanel();
		Spiel.pnlInhalt.setBackground(Fenster.getDesignfarbe());

		this.setBackground(Fenster.getDesignfarbe());

		this.setLayout(new BorderLayout(10, 10));
		this.add(new SpielfeldUI(spiel.getFelder()));
		this.setBorder(new LineBorder(Fenster.getDesignfarbe(), 10));

		JPanel pnlKassen = new JPanel();
		pnlKassen.setLayout(new GridLayout(spiel.getSpieler().size(), 1, 10, 10));
		pnlKassen.setBackground(Fenster.getDesignfarbe());

		for (Spieler spieler : spiel.getSpieler())
		{
			pnlKassen.add(new SpielerUI(spieler, spiel));
		}

		this.add(pnlKassen, BorderLayout.EAST);

		JPanel pnlWest = new JPanel(new BorderLayout(10, 10));
		pnlWest.setBackground(Fenster.getDesignfarbe());

		JButton butImpressum = Fenster.getButtonUeberschrift("DHpoly");
		pnlWest.add(butImpressum, BorderLayout.CENTER);

		JPanel pnlWuerfel = new JPanel(new GridLayout(1, 1));
		pnlWuerfel.add(new WuerfelUI((WuerfelImpl) spiel.getWuerfel(), 1));
		pnlWuerfel.add(new WuerfelUI((WuerfelImpl) spiel.getWuerfel(), 2));
		pnlWuerfel.setBorder(new LineBorder(Fenster.getDesignfarbe(), 10));
		pnlWuerfel.setBackground(Fenster.getDesignfarbe());

		pnlWest.add(pnlWuerfel, BorderLayout.NORTH);

		pnlWest.add(Spiel.pnlInhalt);

		pnlWest.setPreferredSize(new Dimension(500, 1000));

		JButton butWeiter = Fenster.getButtonUeberschrift(spiel.getBeschreibungNaechsterSchritt());
		butWeiter.addActionListener(e -> {
			spiel.naechsterSchritt();
			butWeiter.setText(spiel.getBeschreibungNaechsterSchritt());
		});

		pnlWest.add(butWeiter, BorderLayout.SOUTH);
		this.add(pnlWest, BorderLayout.WEST);

		spiel.addBeobachter(this);
	}
}
