package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.model.FeldDaten;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spieler.model.Spieler;

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
			JButton butSpieler = ElementFactory.getSpielerButton(spieler);
			butSpieler.addActionListener(e -> ansicht.empfange(spieler));
			pnlSpieler.add(butSpieler);
		}

		int randBreite = feld.getSpielerAufFeld().isEmpty() ? 0 : 5;
		pnlSpieler.setBorder(new LineBorder(this.getBackground(), randBreite));

		this.add(pnlSpieler, BorderLayout.SOUTH);
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
