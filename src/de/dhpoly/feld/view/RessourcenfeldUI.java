package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.fehler.control.FehlerImpl;
import de.dhpoly.feld.control.Ressourcenfeld;
import de.dhpoly.ressource.model.Ressource;
import observerpattern.Beobachter;

public class RessourcenfeldUI extends JPanel implements Beobachter
{
	private static final long serialVersionUID = 1L;

	private Component pnlSpieler = new JPanel();

	private Ressourcenfeld feld;

	private JLabel ressourcenbild = new JLabel();

	public RessourcenfeldUI(Ressourcenfeld feld)
	{
		this.feld = feld;

		this.setLayout(new BorderLayout());
		this.setBorder(new LineBorder(Color.BLACK));

		this.add(new JLabel(feld.getBeschriftung()), BorderLayout.NORTH);

		this.add(ressourcenbild);
		ressourcenbild.setIcon(new ImageIcon(
				getRessourcenBild(feld.getRessource()).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
		// aktuell werden die pngs immer auf 60*60 gesized

		hintergrundfarbeSetzen(this);

		feld.addBeobachter(this);
	}

	public void update()
	{
		this.remove(pnlSpieler);
		pnlSpieler = new FeldUI().getSpieler(feld.getSpielerAufFeld());
		this.add(pnlSpieler);
		hintergrundfarbeSetzen(pnlSpieler);
	}

	private void hintergrundfarbeSetzen(Component pnlSpieler2)
	{
		switch (feld.getRessource())
		{
			case HOLZ:
				pnlSpieler2.setBackground(Color.getHSBColor(33, 94, 78));
				break;
			case STEIN:
				pnlSpieler2.setBackground(Color.GRAY);

			default:
				break;
		}
	}

	private ImageIcon getRessourcenBild(Ressource res)
	{

		try
		{
			return new ImageIcon(
					ImageIO.read(new File(".\\src\\de\\dhpoly\\feld\\view\\ressourcen\\" + res.toString() + ".png")));
		}
		catch (IOException ex)
		{
			FehlerImpl.fehlerAufgetreten(ex);
			return new ImageIcon();
		}
	}

}
