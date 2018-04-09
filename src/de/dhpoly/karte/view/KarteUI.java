package de.dhpoly.karte.view;

import javax.swing.JPanel;

import de.dhpoly.karte.Karte;
import de.dhpoly.oberflaeche.ElementFactory;

public class KarteUI extends JPanel
{
	private static final long serialVersionUID = 1L;

	public KarteUI(Karte karte)
	{
		ElementFactory.bearbeitePanel(this);

		// TODO Beschreibung und Titel anzeigen
	}
}
