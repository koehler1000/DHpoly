package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Optional;

import javax.swing.border.LineBorder;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.feld.control.Ereignisfeld;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import observerpattern.Beobachter;

public class EreignisfeldUI extends FeldUI implements Beobachter // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public EreignisfeldUI(Ereignisfeld feld, Optional<SpielfeldAnsicht> ansicht)
	{
		super(feld, ansicht);
		this.setLayout(new BorderLayout());
		this.setBorder(new LineBorder(Color.BLACK));
		this.setBackground(Color.WHITE);

		this.add(ElementFactory.getBild(Bilderverwalter.EREIGNISFELD, Color.WHITE));

		update();
		feld.addBeobachter(this);
	}
}
