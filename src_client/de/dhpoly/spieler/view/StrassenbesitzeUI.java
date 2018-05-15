package de.dhpoly.spieler.view;

import java.awt.GridLayout;

import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spieler.model.Spieler;

public class StrassenbesitzeUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private transient Spieler spieler;

	public StrassenbesitzeUI(Spieler spieler, SpielfeldAnsicht ansicht)
	{
		super(ansicht);
		this.spieler = spieler;

		this.setLayout(new GridLayout(1, spieler.getStrassen().size()));

		for (StrasseDaten feld : spieler.getStrassen())
		{
			this.add(ElementFactory.getTextFeld(feld.getName(), false));
		}
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return false;
	}
}
