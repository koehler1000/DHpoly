package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;

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

	private Feld feld;
	private JPanel pnlSpieler = new JPanel();

	public FeldUI(Feld feld)
	{
		this.feld = feld;
		feld.addBeobachter(this);
	}

	@Override
	public void update()
	{
		this.remove(pnlSpieler);
		pnlSpieler = new JPanel();
		pnlSpieler.setBackground(this.getBackground());

		for (Spieler spieler : feld.getSpielerAufFeld())
		{
			JLabel lblSp = new JLabel(spieler.getName());
			JPanel pnlSp = new JPanel();
			pnlSp.setBackground(SpielerFarben.getSpielerfarbe(spieler.getSpielerNr()));
			pnlSp.add(lblSp);
			pnlSp.setBorder(new LineBorder(Color.BLACK));
			pnlSpieler.add(pnlSp);
		}

		this.add(pnlSpieler, BorderLayout.SOUTH);
	}
}
