package de.dhpoly.feld.view;

import java.awt.Color;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.spieler.Spieler;
import de.dhpoly.spieler.view.SpielerFarben;

public class FeldUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	public FeldUI(List<Spieler> spielerAufFeld, Color color)
	{
		this.setBackground(color);
		for (Spieler spieler : spielerAufFeld)
		{
			JLabel lblSp = new JLabel(spieler.getName());
			JPanel pnlSp = new JPanel();
			pnlSp.setBackground(SpielerFarben.getSpielerfarbe(spieler.getSpielerNr()));
			pnlSp.add(lblSp);
			pnlSp.setBorder(new LineBorder(Color.BLACK));
			this.add(pnlSp);
		}
	}
}
