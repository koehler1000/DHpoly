package de.dhpoly.feld.view;

import java.awt.GridLayout;
import java.util.List;

import de.dhpoly.feld.model.Strasse;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class HaeuserUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public HaeuserUI(List<Strasse> felder, SpielfeldAnsicht ansicht)
	{
		super(ansicht);
		this.setLayout(new GridLayout(felder.size(), 1, 10, 10));

		for (Strasse strasse : felder)
		{
			this.add(new HausUI(strasse, ansicht));
		}
	}

	@Override
	public boolean isEinmalig()
	{
		return false;
	}
}
