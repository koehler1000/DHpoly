package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.model.FeldDaten;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spieler.model.Spieler;
import de.dhpoly.spieler.view.SpielerFarben;

public abstract class FeldUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public FeldUI(FeldDaten feld, SpielfeldAnsicht ansicht)
	{
		super(ansicht);

		this.setLayout(new BorderLayout());
		this.setBackground(getHintergrundfarbe());

		JPanel pnlSpieler = new JPanel();
		pnlSpieler.setLayout(new GridLayout(1, 1));
		pnlSpieler.setBackground(getHintergrundfarbe());

		for (Spieler spieler : feld.getSpielerAufFeld())
		{
			pnlSpieler.add(getPanel(spieler));
		}

		int randBreite = feld.getSpielerAufFeld().isEmpty() ? 0 : 5;
		pnlSpieler.setBorder(new LineBorder(this.getBackground(), randBreite));

		this.add(pnlSpieler, BorderLayout.SOUTH);
	}

	private JPanel getPanel(Spieler spieler)
	{
		JPanel pnlSp = new JPanel();
		pnlSp.setBackground(SpielerFarben.getSpielerfarbe(spieler.getSpielerNr()));
		pnlSp.add(new JLabel(spieler.getName()));
		pnlSp.setBorder(new LineBorder(Color.BLACK));
		return pnlSp;
	}

	public Color getHintergrundfarbe()
	{
		return Color.WHITE;
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return true;
	}
}
