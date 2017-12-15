package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.feld.control.Losfeld;
import de.dhpoly.fenster.view.Bild;
import de.dhpoly.fenster.view.Fenster;
import observerpattern.Beobachter;

public class LosfeldUI extends Bild implements Beobachter
{
	private static final long serialVersionUID = 1L;

	private Losfeld feld;
	private Component pnlSpieler = new JPanel();

	public LosfeldUI(Losfeld feld)
	{
		super(Bilderverwalter.LOSFELD, Fenster.getBilderverwalter());
		this.feld = feld;
		this.setLayout(new BorderLayout());
		this.setBorder(new LineBorder(Color.BLACK));
		this.setBackground(Color.WHITE);

		update();

		feld.addBeobachter(this);
	}

	public void update()
	{
		this.remove(pnlSpieler);
		pnlSpieler = new FeldUI(feld.getSpielerAufFeld(), this.getBackground());
		this.add(pnlSpieler, BorderLayout.SOUTH);
	}
}
