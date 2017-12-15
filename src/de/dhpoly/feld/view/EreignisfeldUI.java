package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.feld.control.Ereignisfeld;
import de.dhpoly.fenster.view.Bild;
import de.dhpoly.fenster.view.Fenster;
import observerpattern.Beobachter;

public class EreignisfeldUI extends Bild implements Beobachter
{
	private static final long serialVersionUID = 1L;
	private Ereignisfeld feld;
	private Component pnlSpieler = new JPanel();

	public EreignisfeldUI(Ereignisfeld feld)
	{
		super(Bilderverwalter.EREIGNISFELD, Fenster.getBilderverwalter());
		this.feld = feld;
		pnlSpieler = new JPanel();
		this.setLayout(new BorderLayout());
		this.setBorder(new LineBorder(Color.BLACK));
		this.setBackground(Color.WHITE);

		this.add(pnlSpieler, BorderLayout.SOUTH);

		update();
		feld.addBeobachter(this);
	}

	@Override
	public void update()
	{
		this.remove(pnlSpieler);
		if (feld.getSpielerAufFeld().size() > 0)
		{
			pnlSpieler = new FeldUI(feld.getSpielerAufFeld(), this.getBackground());
			this.add(pnlSpieler, BorderLayout.SOUTH);
		}
	}
}
