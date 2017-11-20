package de.dhpoly.feld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.dhpoly.fehler.control.FehlerImpl;
import de.dhpoly.feld.control.Ereignisfeld;
import observerpattern.Beobachter;

public class EreignisfeldUI extends JPanel implements Beobachter
{
	private static final long serialVersionUID = 1L;
	private Ereignisfeld feld;
	private Component pnlSpieler = new JPanel();

	public EreignisfeldUI(Ereignisfeld feld)
	{
		this.feld = feld;
		pnlSpieler = new JPanel();
		this.setLayout(new BorderLayout());
		this.setBorder(new LineBorder(Color.BLACK));
		this.setBackground(Color.WHITE);
		this.add(new JLabel(feld.getBeschriftung()), BorderLayout.NORTH);

		JLabel lblBild = new JLabel();
		lblBild.setIcon(getBild());
		this.add(lblBild, BorderLayout.CENTER);

		this.add(pnlSpieler);

		update();
		feld.addBeobachter(this);
	}

	private ImageIcon getBild()
	{
		try
		{
			return new ImageIcon(ImageIO.read(new File(".\\pics\\default\\karte\\ereigniskarte.jpg")));
		}
		catch (IOException ex)
		{
			FehlerImpl.fehlerAufgetreten(ex);
			return new ImageIcon();
		}
	}

	@Override
	public void update()
	{
		this.remove(pnlSpieler);
		pnlSpieler = new FeldUI(feld.getSpielerAufFeld(), this.getBackground());
		this.add(pnlSpieler);
	}
}
