package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.feld.control.Ereignisfeld;
import observerpattern.Beobachter;

public class EreignisfeldUI extends JPanel implements Beobachter
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
		feld.addBeobachter(this);
	}

	@Override
	public void update()
	{
		this.remove(pnlSpieler);
		pnlSpieler = new FeldUI().getSpieler(feld.getSpielerAufFeld());
		this.add(pnlSpieler);
	}
}
