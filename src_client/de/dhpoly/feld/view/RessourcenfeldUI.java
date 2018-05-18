package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.border.LineBorder;

import de.dhpoly.bilderverwalter.view.Bild;
import de.dhpoly.datenobjekt.Datenobjekt;
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
		this.setBorder(new LineBorder(Color.BLACK));

		hintergrundfarbeSetzen();
		Bild bild = ElementFactory.getBild(feld2.getRessource());
		bild.setBackground(this.getBackground());
		this.add(bild, BorderLayout.CENTER);
	}

	private void hintergrundfarbeSetzen()
	{
		this.setBackground(feld.getRessource().getFarbe());
	}

	@Override
	public void zeige(String beschreibung, Datenobjekt objekt)
	{
		// wird nicht direkt angezeigt
	}
}
