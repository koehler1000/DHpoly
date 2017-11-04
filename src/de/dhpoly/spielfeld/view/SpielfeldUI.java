package de.dhpoly.spielfeld.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JPanel;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.Strasse;
import de.dhpoly.feld.view.StrasseUI;

public class SpielfeldUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	public SpielfeldUI(List<Feld> spielfelder)
	{
		this.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;

		for (Feld feld : spielfelder)
		{
			JPanel panel = new StrasseUI((Strasse) feld);
			this.add(panel, c);

			c.gridx++;
		}

	}
}
