package de.dhpoly.feld.view;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class HaeuserUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public HaeuserUI(List<StrasseDaten> felder, SpielfeldAnsicht ansicht)
	{
		super(ansicht);

		JPanel pnlInhalt = ElementFactory.erzeugePanel();
		pnlInhalt.setLayout(new GridLayout(felder.size(), 1, 10, 10));

		for (StrasseDaten strasse : felder)
		{
			pnlInhalt.add(new HausUI(strasse, ansicht));
		}

		this.add(pnlInhalt);
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return false;
	}
}
