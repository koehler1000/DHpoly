package de.dhpoly.feld.view;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.Strasse;

public class HaeuserUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	public HaeuserUI(List<Feld> felder)
	{
		this.setLayout(new GridLayout(1, 1, 10, 10));

		for (Feld feld : felder)
		{
			if (feld instanceof Strasse)
			{
				this.add(new HausUI((Strasse) feld));
			}
		}
	}
}
