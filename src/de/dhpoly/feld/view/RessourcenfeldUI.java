package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.feld.control.Ressourcenfeld;
import de.dhpoly.ressource.model.Ressource;
import observerpattern.Beobachter;

public class RessourcenfeldUI extends JPanel implements Beobachter
{
	private static final long serialVersionUID = 1L;

	private Component pnlSpieler = new JPanel();

	private Ressourcenfeld feld;

	private JLabel ressourcenbild;

	public RessourcenfeldUI(Ressourcenfeld feld)
	{
		this.feld = feld;

		this.setLayout(new BorderLayout());
		this.setBorder(new LineBorder(Color.BLACK));

		// aktuell werden die pngs immer auf 60*60 gesized
		ressourcenbild = new JLabel(new ImageIcon(
				getRessourcenBild(feld.getRessource()).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));

		this.add(ressourcenbild, BorderLayout.CENTER);
		feld.addBeobachter(this);
		hintergrundfarbeSetzen();
	}

	public void update()
	{
		this.remove(pnlSpieler);
		pnlSpieler = new FeldUI(feld.getSpielerAufFeld(), this.getBackground());
		this.add(pnlSpieler);
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

	private ImageIcon getRessourcenBild(Ressource res)
	{
		return Bilderverwalter.getBild(res);
	}
}
