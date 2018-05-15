package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.border.LineBorder;

import de.dhpoly.bilderverwalter.view.Bild;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spielfeld.model.RessourcenfeldDaten;

public class RessourcenfeldUI extends FeldUI // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private RessourcenfeldDaten feld;

	public RessourcenfeldUI(RessourcenfeldDaten feld2, SpielfeldAnsicht ansicht)
	{
		super(feld2, ansicht);
		this.feld = feld2;

		this.setLayout(new BorderLayout());
		this.setBorder(new LineBorder(Color.BLACK));

		hintergrundfarbeSetzen();
		Bild bild = ElementFactory.getBild(feld2.getRessource());
		bild.setBackground(this.getBackground());
		this.add(bild, BorderLayout.CENTER);
	}

	private void hintergrundfarbeSetzen()
	{
		switch (feld.getRessource())
		{
			case HOLZ:
				this.setBackground(Color.getHSBColor(33, 94, 78));
				break;
			case STEIN:
				this.setBackground(new Color(220, 220, 220));
				break;
			default:
				break;
		}
	}
}
