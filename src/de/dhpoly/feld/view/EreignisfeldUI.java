package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.border.LineBorder;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.feld.control.Ereignisfeld;
import de.dhpoly.fenster.view.Fenster;
import observerpattern.Beobachter;

public class EreignisfeldUI extends FeldUI implements Beobachter //NOSONAR
{
	private static final long serialVersionUID = 1L;

	public EreignisfeldUI(Ereignisfeld feld)
	{
		super(feld);
		this.setLayout(new BorderLayout());
		this.setBorder(new LineBorder(Color.BLACK));
		this.setBackground(Color.WHITE);

		this.add(Fenster.getBild(Bilderverwalter.EREIGNISFELD, Color.WHITE));

		update();
		feld.addBeobachter(this);
	}
}
