package de.dhpoly.wuerfel.view;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WuerfelUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	public WuerfelUI(int ergebnis1, int ergebnis2)
	{
		this.setLayout(new GridLayout(1, 2, 10, 10));

		this.add(getWuerfel(ergebnis1));
		this.add(getWuerfel(ergebnis2));
	}

	private Component getWuerfel(int nummer)
	{
		
		String wuerfelpfad = "wuerfel_"+nummer+".png";
		BufferedImage wuerfel_pic = null;
		System.out.println(wuerfelpfad);
		try {
			wuerfel_pic = ImageIO.read(new File(wuerfelpfad));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		JLabel wuerfel = new JLabel(new ImageIcon(wuerfel_pic));
		
		
		return wuerfel;
	}
}
