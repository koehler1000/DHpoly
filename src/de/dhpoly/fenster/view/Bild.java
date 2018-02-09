package de.dhpoly.fenster.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import de.dhpoly.bilderverwalter.Bilderverwalter;

public class Bild extends JPanel
{
	private static final long serialVersionUID = 1L;
	private String bildPfad;
	private transient Bilderverwalter bilderverwalter;

	public Bild(String pfad, Bilderverwalter bilderverwalter)
	{
		bildPfad = pfad;
		this.bilderverwalter = bilderverwalter;
	}

	public void setBildPfad(String pfad)
	{
		bildPfad = pfad;
		this.repaint();
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);

		Image image = bilderverwalter.getBild(bildPfad).getImage();

		int hoehe = this.getHeight() - 20;
		int breite = this.getWidth() - 20;

		g.drawImage(image, 10, 10, breite, hoehe, this);
	}
}
