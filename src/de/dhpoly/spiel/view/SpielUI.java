package de.dhpoly.spiel.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.Oberflaeche;
import de.dhpoly.spiel.Spiel;
import de.dhpoly.spielfeld.view.SpielfeldUI;
import de.dhpoly.wuerfel.control.WuerfelImpl;
import de.dhpoly.wuerfel.view.WuerfelUI;

public class SpielUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	public SpielUI(Spiel spiel)
	{
		ElementFactory.bearbeitePanel(this);

		this.add(new SpielfeldUI(spiel.getFelder()));

		this.add(new SpielerUebersichtUI(spiel), BorderLayout.EAST);

		JPanel pnlWest = ElementFactory.erzeugePanel();

		JButton butImpressum = ElementFactory.getButtonUeberschrift("DHpoly");
		pnlWest.add(butImpressum, BorderLayout.CENTER);

		JPanel pnlWuerfel = ElementFactory.erzeugePanel();
		pnlWuerfel.setLayout(new GridLayout(1, 1));
		pnlWuerfel.add(new WuerfelUI((WuerfelImpl) spiel.getWuerfel(), 1));
		pnlWuerfel.add(new WuerfelUI((WuerfelImpl) spiel.getWuerfel(), 2));

		pnlWest.add(pnlWuerfel, BorderLayout.NORTH);

		pnlWest.add(Oberflaeche.getInstance().getRand());

		pnlWest.setPreferredSize(new Dimension(500, 1000));

		JButton butWeiter = ElementFactory.getButtonUeberschrift(spiel.getBeschreibungNaechsterSchritt());
		butWeiter.addActionListener(e -> {
			spiel.naechsterSchritt();
			butWeiter.setText(spiel.getBeschreibungNaechsterSchritt());
		});

		pnlWest.add(butWeiter, BorderLayout.SOUTH);
		this.add(pnlWest, BorderLayout.WEST);
	}
}
