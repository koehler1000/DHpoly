package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.border.LineBorder;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.feld.control.FeldLos;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import observerpattern.Beobachter;

public class LosfeldUI extends FeldUI implements Beobachter // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public LosfeldUI(FeldLos feld, SpielfeldAnsicht ansicht)
	{
		super(feld, ansicht);
		this.setLayout(new BorderLayout());
		this.setBorder(new LineBorder(Color.BLACK));
		this.setBackground(Color.WHITE);

		this.add(ElementFactory.getBild(Bilderverwalter.LOSFELD, Color.WHITE));

		update();
	}
}
