package de.dhpoly.fehler.view;

import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class FehlerUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public FehlerUI(String fehler, SpielfeldAnsicht ansicht)
	{
		super(ansicht);
		this.add(ElementFactory.getNachrichtPanel("Fehler", fehler));
	}
}
