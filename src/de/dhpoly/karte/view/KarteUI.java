package de.dhpoly.karte.view;

import de.dhpoly.karte.Karte;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class KarteUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public KarteUI(Karte karte, SpielfeldAnsicht ansicht)
	{
		super(ansicht);

		ElementFactory.getNachrichtPanel(karte.getTitel(), karte.getBeschreibung());
	}
}
