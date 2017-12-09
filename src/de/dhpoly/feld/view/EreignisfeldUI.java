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

		ImageIcon ico = new ImageIcon("/home/webs/bild.jpg");
		ico.setImage(ico.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));

		ImageIcon bild = Bilderverwalter.getBild("spielfeld\\ereignis.png");

		JLabel lblBild = new JLabel(bild);
		this.add(lblBild, BorderLayout.CENTER);
		this.add(pnlSpieler, BorderLayout.SOUTH);

		update();
		feld.addBeobachter(this);
	}

	@Override
	public void update()
	{
		this.remove(pnlSpieler);
		pnlSpieler = new FeldUI(feld.getSpielerAufFeld(), this.getBackground());
		this.add(pnlSpieler, BorderLayout.SOUTH);
	}
}
