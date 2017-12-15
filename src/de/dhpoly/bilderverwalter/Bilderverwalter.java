package de.dhpoly.bilderverwalter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

	public static final String LOSFELD = "spielfeld\\los.png";

	private static Map<String, ImageIcon> bilder = new HashMap<>();

	public String getPfad(Ressource ressource)
	{
		switch (ressource)
		{
			case GELD:
				return RESSOURCE_GELD;
			case HOLZ:
				return RESSOURCE_HOLZ;
			case STEIN:
				return RESSOURCE_STEIN;
			default:
				return "";
		}
	}

	public ImageIcon getWuerfelBild(int nummer)
	{
		String pfad = WUERFEL.replace("%", "" + nummer);
		return getBild(pfad);
	}

	public ImageIcon getBild(String pfad)
	{
		if (bilder.containsKey(pfad))
		{
			return bilder.get(pfad);
		}

		try
		{
			ImageIcon icon = new ImageIcon(ImageIO.read(new File(".\\pics\\default\\" + pfad)));
			bilder.put(pfad, icon);
			return icon;
		}
		catch (IOException ex)
		{
			FehlerImpl.stillerFehler(ex + " (" + pfad + ")");
			ImageIcon icon = new ImageIcon();
			bilder.put(pfad, icon);
			return icon;
		}
	}

	public String getWuerfelPfad(int i)
	{
		return WUERFEL.replace("%", "" + i);
	}
}
