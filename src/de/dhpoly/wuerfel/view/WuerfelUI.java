package de.dhpoly.wuerfel.view;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.dhpoly.fehler.control.FehlerImpl;
import de.dhpoly.wuerfel.Wuerfel;
import de.dhpoly.wuerfel.control.WuerfelImpl;

public class WuerfelUI extends JPanel implements Observer
{
	private static final long serialVersionUID = 1L;

	private Component component = new JPanel();

	private int wuerfelNr = 0;

	private Wuerfel wuerfel;

	public WuerfelUI(WuerfelImpl wuerfel, int wuerfelNr)
	{
		this.wuerfel = wuerfel;
		this.wuerfelNr = wuerfelNr;
		wuerfel.addObserver(this);

		update();
	}

	private Component getWuerfel(int nummer) throws IOException
	{
		String wuerfelpfad = "wuerfel_" + nummer + ".png";
		BufferedImage wuerfel_pic = null;
		wuerfel_pic = ImageIO.read(new File(wuerfelpfad));
		JLabel wuerfel = new JLabel(new ImageIcon(wuerfel_pic));

		return wuerfel;
	}

	@Override
	public void update(Observable o, Object arg)
	{
		update();
	}

	private void update()
	{
		this.remove(component);

		try
		{
			switch (wuerfelNr)
			{
				case 1:
					component = getWuerfel(wuerfel.getWuerfelErgebnis1());
					break;
				case 2:
					component = getWuerfel(wuerfel.getWuerfelErgebnis2());
					break;
			}
		}
		catch (IOException ex)
		{
			FehlerImpl.fehlerAufgetreten(ex);
		}

		this.add(component);
	}
}
