package de.dhpoly.bilderverwalter.control;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.fehler.control.FehlerImpl;

public class BilderverwalterImpl implements Bilderverwalter
{

	@Override
	public ImageIcon getBild(String ordnername, String bildnameMitDateiendung)
	{
		return getBild(ordnername + "\\" + bildnameMitDateiendung);
	}

	@Override
	public ImageIcon getBild(String pfad)
	{
		try
		{
			return new ImageIcon(ImageIO.read(new File(".\\pics\\default\\" + pfad)));
		}
		catch (IOException ex)
		{
			FehlerImpl.fehlerAufgetreten(ex);
			return new ImageIcon();
		}
	}
}
