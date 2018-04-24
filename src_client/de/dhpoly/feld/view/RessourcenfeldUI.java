package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.border.LineBorder;

import de.dhpoly.bilderverwalter.view.Bild;
import de.dhpoly.feld.control.FeldRessource;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import observerpattern.Beobachter;

public class RessourcenfeldUI extends FeldUI implements Beobachter // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private transient FeldRessource feld;

	public RessourcenfeldUI(FeldRessource feld, SpielfeldAnsicht ansicht)
	{
		super(feld, ansicht);
		this.feld = feld;

		this.setLayout(new BorderLayout());
		this.setBorder(new LineBorder(Color.BLACK));

		hintergrundfarbeSetzen();
		Bild bild = ElementFactory.getBild(feld.getRessource());
		bild.setBackground(this.getBackground());
		this.add(bild, BorderLayout.CENTER);

		feld.addBeobachter(this);
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