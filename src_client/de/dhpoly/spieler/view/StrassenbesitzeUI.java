package de.dhpoly.spieler.view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import de.dhpoly.feld.model.StrasseDaten;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielUI;
import de.dhpoly.spieler.model.Spieler;

public class StrassenbesitzeUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public StrassenbesitzeUI(Spieler spieler, SpielUI ansicht)
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
	public void zeige(String beschreibung)
	{
		// wird nicht direkt angezeigt
	}

	@Override
	public List<Oberflaeche> durchHinzufuegenUngueltigWerdend(List<Oberflaeche> oberflaechen)
	{
		return new ArrayList<>();
	}
}
