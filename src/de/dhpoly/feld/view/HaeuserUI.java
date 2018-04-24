package de.dhpoly.feld.view;

import java.awt.GridLayout;
import java.util.List;

import de.dhpoly.feld.Feld;
import de.dhpoly.feld.control.FeldStrasse;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class HaeuserUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public HaeuserUI(List<Feld> felder, SpielfeldAnsicht ansicht)
	{
		super(ansicht);
		this.setLayout(new GridLayout(felder.size(), 1, 10, 10));

		for (Feld feld : felder)
		{
			if (feld instanceof FeldStrasse)
			{
				this.add(new HausUI((FeldStrasse) feld));
			}
		}
	}
}
