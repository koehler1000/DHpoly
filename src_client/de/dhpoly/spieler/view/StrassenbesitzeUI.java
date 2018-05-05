package de.dhpoly.spieler.view;

import java.awt.GridLayout;

import de.dhpoly.feld.model.Strasse;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spieler.model.Spieler;
import observerpattern.Beobachter;

public class StrassenbesitzeUI extends Oberflaeche implements Beobachter // NOSONAR
{
	private static final long serialVersionUID = 1L;

	private transient Spieler spieler;

	public StrassenbesitzeUI(Spieler spieler, SpielfeldAnsicht ansicht)
	{
		super(ansicht);
		this.spieler = spieler;

		update();
	}

	@Override
	public void update()
	{
		this.setLayout(new GridLayout(1, spieler.getStrassen().size()));

		for (Strasse feld : spieler.getStrassen())
		{
			this.add(ElementFactory.getTextFeld(feld.getName(), false));
		}
	}

	@Override
	public boolean isEinmalig()
	{
		return false;
	}
}
