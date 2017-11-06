package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.control.Losfeld;

public class LosfeldUI extends JPanel implements Observer
{
	private static final long serialVersionUID = 1L;

	private Losfeld feld;
	private Component pnlSpieler = new JPanel();

	public LosfeldUI(Losfeld feld)
	{
		this.feld = feld;
		this.setLayout(new BorderLayout());
		this.setBorder(new LineBorder(Color.BLACK));
		this.setBackground(Color.WHITE);
		JLabel lblLosBeschriftung = new JLabel(feld.getBeschriftung());
		this.add(lblLosBeschriftung);
		update();
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		update();
	}

	private void update()
	{
		this.remove(pnlSpieler);
		pnlSpieler = new FeldUI().getSpieler(feld.getSpielerAufFeld());
		this.add(pnlSpieler, BorderLayout.SOUTH);
	}
}
