package de.dhpoly.spieler.view;

import java.awt.GridLayout;

import javax.swing.JPanel;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;
import de.dhpoly.spieler.model.Spieler;

public class StrassenbesitzeUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public StrassenbesitzeUI(Spieler spieler, SpielfeldAnsicht ansicht)
	{
		super(ansicht);

		JPanel pnlInhalt = ElementFactory.erzeugePanel();
		pnlInhalt.setLayout(new GridLayout(1, spieler.getStrassen().size()));

		for (StrasseDaten feld : spieler.getStrassen())
		{
			pnlInhalt.add(ElementFactory.getTextFeld(feld.getName(), false));
		}

		this.add(pnlInhalt);
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return false;
	}

	@Override
	public void zeige(String beschreibung, Datenobjekt objekt)
	{
		// wird nicht direkt angezeigt
	}
}
