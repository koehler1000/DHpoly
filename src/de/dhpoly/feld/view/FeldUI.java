package de.dhpoly.feld.view;

import java.awt.Component;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.view.SpielerFarben;

public class FeldUI
{
	public Component getSpieler(List<Spieler> spielerAufFeld)
	{
		JPanel pnlSpieler = new JPanel();
		for (Spieler spieler : spielerAufFeld)
		{
			JLabel lblSp = new JLabel(spieler.getName());
			JPanel pnlSp = new JPanel();
			pnlSp.setBackground(SpielerFarben.getSpielerfarbe(spieler.getSpielerNr()));
			pnlSp.add(lblSp);
			pnlSpieler.add(pnlSp);
		}
		return pnlSpieler;
	}
}
