package de.dhpoly.bilderverwalter;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import de.dhpoly.fehler.control.FehlerImpl;
import de.dhpoly.ressource.model.Ressource;

public class Bilderverwalter
{
	public static final String LOGO = "spiel\\logo.png";

	public static final String RESSOURCE_STEIN = "ressourcen\\stein.png";
	public static final String RESSOURCE_HOLZ = "ressourcen\\holz.png";
	public static final String RESSOURCE_GELD = "ressourcen\\geld.png";

	private static final String WUERFEL = "wuerfel//wuerfel_%.png"; // % wird zu Nummer des Würfels

	public static ImageIcon getBild(Ressource ressource)
	{
		switch (ressource)
		{
			case GELD:
				return getBild(RESSOURCE_GELD);
			case HOLZ:
				return getBild(RESSOURCE_HOLZ);
			case STEIN:
				return getBild(RESSOURCE_STEIN);
			default:
				return new ImageIcon();
		}
	}

	public static ImageIcon getWuerfelBild(int nummer)
	{
		String pfad = WUERFEL.replace("%", "" + nummer);
		return Bilderverwalter.getBild(pfad);
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
