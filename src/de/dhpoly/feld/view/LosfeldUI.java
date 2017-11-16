package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.control.Losfeld;
import observerpattern.Beobachter;

public class LosfeldUI extends JPanel implements Beobachter
{
	private static final long serialVersionUID = 1L;

	private Losfeld feld;
	private Component pnlSpieler = new JPanel();

	public LosfeldUI(Losfeld feld)
	{
		this.feld = feld;
		this.setLayout(new BorderLayout());
		this.setBorder(new LineBorder(Color.BLACK));

		this.add(new JLabel(feld.getBeschriftung()), BorderLayout.NORTH);
		update();

		feld.addBeobachter(this);
	}

	public void update()
	{
		this.remove(pnlSpieler);
		pnlSpieler = new FeldUI().getSpieler(feld.getSpielerAufFeld());
		this.add(pnlSpieler, BorderLayout.SOUTH);
	}
}
