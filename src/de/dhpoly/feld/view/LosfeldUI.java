package de.dhpoly.feld.view;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.control.Losfeld;

public class LosfeldUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	public LosfeldUI(Losfeld feld)
	{
		this.setBorder(new LineBorder(Color.BLACK));
		this.setBackground(Color.WHITE);
		JLabel lblLosBeschriftung = new JLabel(feld.getBeschriftung());
		this.add(lblLosBeschriftung);
	}

}
