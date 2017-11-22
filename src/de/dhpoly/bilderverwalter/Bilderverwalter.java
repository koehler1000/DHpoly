package de.dhpoly.bilderverwalter;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import de.dhpoly.fehler.control.FehlerImpl;

public class Bilderverwalter
{
	public static ImageIcon getBild(String ordnername, String bildnameMitDateiendung)
	{
		return getBild(ordnername + "\\" + bildnameMitDateiendung);
	}

	public static ImageIcon getBild(String pfad)
	{
		try
		{
			return new ImageIcon(ImageIO.read(new File(".\\pics\\default\\" + pfad)));
		}
		catch (IOException ex)
		{
			FehlerImpl.fehlerAufgetreten(ex + " (" + pfad + ")");
			return new ImageIcon();
		}
	}
}
