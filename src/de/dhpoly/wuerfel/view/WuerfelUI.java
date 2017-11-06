package de.dhpoly.wuerfel.view;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.dhpoly.fehler.control.FehlerImpl;

public class WuerfelUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	public WuerfelUI(int ergebnis1, int ergebnis2)
	{
		this.setLayout(new GridLayout(1, 2, 10, 10));

		try
		{
			this.add(getWuerfel(ergebnis1));
			this.add(getWuerfel(ergebnis2));
		}
		catch (IOException ex)
		{
			FehlerImpl.fehlerAufgetreten(ex);
		}
	}

	private Component getWuerfel(int nummer) throws IOException
	{
		switch (nummer)
		{
			case 1:
				JLabel lblBild = new JLabel();
				return lblBild;
			default:
				JTextArea wuerfel = new JTextArea();
				wuerfel.setFont(new Font("arial", Font.BOLD, 30));
				wuerfel.setEditable(false);
				wuerfel.setText("" + nummer);
				return wuerfel;
		}

	}
}
