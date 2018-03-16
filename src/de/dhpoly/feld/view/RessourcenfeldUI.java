package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.border.LineBorder;

import de.dhpoly.feld.control.Ressourcenfeld;
import de.dhpoly.fenster.view.Bild;
import de.dhpoly.oberflaeche.ElementFactory;
import observerpattern.Beobachter;

public class RessourcenfeldUI extends FeldUI implements Beobachter // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private transient Ressourcenfeld feld;

	public RessourcenfeldUI(Ressourcenfeld feld)
	{
		super(feld);
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
