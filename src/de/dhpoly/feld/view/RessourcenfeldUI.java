package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.feld.control.Ressourcenfeld;
import de.dhpoly.fenster.view.Bild;
import de.dhpoly.fenster.view.Fenster;
import observerpattern.Beobachter;

public class RessourcenfeldUI extends Bild implements Beobachter
{
	private static final long serialVersionUID = 1L;

	private Component pnlSpieler = new JPanel();

	private Ressourcenfeld feld;

	public RessourcenfeldUI(Ressourcenfeld feld)
	{
		super(Bilderverwalter.getPfad(feld.getRessource()), Fenster.getBilderverwalter());
		this.feld = feld;

		this.setLayout(new BorderLayout());
		this.setBorder(new LineBorder(Color.BLACK));

		feld.addBeobachter(this);
		hintergrundfarbeSetzen();
	}

	public void update()
	{
		this.remove(pnlSpieler);
		pnlSpieler = new FeldUI(feld.getSpielerAufFeld(), this.getBackground());
		this.add(pnlSpieler, BorderLayout.SOUTH);
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
			default:
				break;
		}
	}
}
