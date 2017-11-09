package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.control.Ereignisfeld;

public class EreignisfeldUI extends JPanel implements Observer
{
	private static final long serialVersionUID = 1L;
	private Ereignisfeld feld;
	private Component pnlSpieler;

	public EreignisfeldUI(Ereignisfeld feld)
	{
		this.feld = feld;
		pnlSpieler = new JPanel();
		this.setLayout(new BorderLayout());
		this.setBorder(new LineBorder(Color.BLACK));

		this.add(new JLabel(feld.getBeschriftung()), BorderLayout.NORTH);

		update();
		feld.addObserver(this);
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
		this.add(pnlSpieler);
	}
}
