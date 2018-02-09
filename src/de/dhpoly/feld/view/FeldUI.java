package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.Feld;
import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.view.SpielerFarben;
import observerpattern.Beobachter;

public class FeldUI extends JPanel implements Beobachter
{
	private static final long serialVersionUID = 1L;

	private transient Feld feld;
	private JPanel pnlSpieler = new JPanel();
	private transient Map<Spieler, JPanel> spielerMap = new HashMap<>();

	public FeldUI(Feld feld)
	{
		this.feld = feld;
		feld.addBeobachter(this);

		this.setLayout(new BorderLayout());

		pnlSpieler.setLayout(new GridLayout(1, 1));
		pnlSpieler.setBorder(new LineBorder(this.getBackground(), 5));

		this.add(pnlSpieler, BorderLayout.SOUTH);
	}

	private JPanel getPanel(Spieler spieler)
	{
		if (spielerMap.containsKey(spieler))
		{
			return spielerMap.get(spieler);
		}
		else
		{
			JLabel lblSp = new JLabel(spieler.getName());
			JPanel pnlSp = new JPanel();
			pnlSp.setBackground(SpielerFarben.getSpielerfarbe(spieler.getSpielerNr()));
			pnlSp.add(lblSp);
			pnlSp.setBorder(new LineBorder(Color.BLACK));
			pnlSpieler.add(pnlSp);

			spielerMap.put(spieler, pnlSp);

			return getPanel(spieler);
		}
	}

	@Override
	public void update()
	{
		for (Spieler spieler : spielerMap.keySet())
		{
			pnlSpieler.remove(getPanel(spieler));
		}

		for (Spieler spieler : feld.getSpielerAufFeld())
		{
			pnlSpieler.add(getPanel(spieler));
		}

		this.add(pnlSpieler, BorderLayout.SOUTH);
		this.revalidate();

		pnlSpieler.setBorder(new LineBorder(this.getBackground(), 5));
	}
}
