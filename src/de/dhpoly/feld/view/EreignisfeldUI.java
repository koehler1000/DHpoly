package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.feld.control.Ereignisfeld;
import de.dhpoly.fenster.view.Fenster;
import observerpattern.Beobachter;

public class EreignisfeldUI extends FeldUI implements Beobachter
{
	private static final long serialVersionUID = 1L;
	private Component pnlSpieler = new JPanel();

	public EreignisfeldUI(Ereignisfeld feld)
	{
		super(feld);
		pnlSpieler = new JPanel();
		this.setLayout(new BorderLayout());
		this.setBorder(new LineBorder(Color.BLACK));
		this.setBackground(Color.WHITE);

		this.add(Fenster.getBild(Bilderverwalter.EREIGNISFELD, Color.WHITE));
		this.add(pnlSpieler, BorderLayout.SOUTH);

		update();
		feld.addBeobachter(this);
	}

	// @Override
	// public void update()
	// {
	// this.remove(pnlSpieler);
	// if (feld.getSpielerAufFeld().size() > 0)
	// {
	// pnlSpieler = new FeldUI(feld.getSpielerAufFeld(), this.getBackground());
	// this.add(pnlSpieler, BorderLayout.SOUTH);
	// }
	// }
}
