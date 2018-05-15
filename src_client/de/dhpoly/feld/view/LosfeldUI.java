package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.border.LineBorder;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.feld.model.LosfeldDaten;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class LosfeldUI extends FeldUI // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public LosfeldUI(LosfeldDaten feld, SpielfeldAnsicht ansicht)
	{
		super(feld, ansicht);
		this.setLayout(new BorderLayout());
		this.setBorder(new LineBorder(Color.BLACK));
		this.setBackground(Color.WHITE);

		this.add(ElementFactory.getBild(Bilderverwalter.LOSFELD, Color.WHITE));
	}
}
