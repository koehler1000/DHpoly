package de.dhpoly.wuerfel.view;

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

	private JLabel lblWuerfelBild = new JLabel();

	private int wuerfelNr = 0;

	private Wuerfel wuerfel;

	public WuerfelUI(WuerfelImpl wuerfel, int wuerfelNr)
	{
		this.wuerfel = wuerfel;
		this.wuerfelNr = wuerfelNr;

		this.add(lblWuerfelBild);

		wuerfel.addObserver(this);
		update();
	}

	private ImageIcon getWuerfelBild(int nummer)
	{
		try
		{
			return new ImageIcon(ImageIO.read(new File(".\\pics\\default\\wuerfel\\wuerfel_" + nummer + ".png")));
		}
		catch (IOException ex)
		{
			FehlerImpl.fehlerAufgetreten(ex);
			return new ImageIcon();
		}
	}

	@Override
	public void update(Observable o, Object arg)
	{
		update();
	}

	private void update()
	{
		if (wuerfelNr == 1)
		{
			lblWuerfelBild.setIcon(getWuerfelBild(wuerfel.getWuerfelErgebnis1()));
		}
		else
		{
			lblWuerfelBild.setIcon(getWuerfelBild(wuerfel.getWuerfelErgebnis2()));
		}
	}
}
