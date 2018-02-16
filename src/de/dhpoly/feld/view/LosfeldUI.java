package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.border.LineBorder;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.feld.control.Losfeld;
import de.dhpoly.fenster.view.Fenster;
import observerpattern.Beobachter;

public class LosfeldUI extends FeldUI implements Beobachter //NOSONAR
{
	private static final long serialVersionUID = 1L;

	public LosfeldUI(Losfeld feld)
	{
		super(feld);
		this.setLayout(new BorderLayout());
		this.setBorder(new LineBorder(Color.BLACK));
		this.setBackground(Color.WHITE);

		this.add(Fenster.getBild(Bilderverwalter.LOSFELD, Color.WHITE));

		update();

		feld.addBeobachter(this);
	}
}
