package de.dhpoly.nachricht.view;

import de.dhpoly.nachricht.model.Nachricht;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class NachrichtUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public NachrichtUI(Nachricht nachricht, SpielfeldAnsicht ansicht)
	{
		super(ansicht);
		this.add(ElementFactory.getNachrichtPanel("Nachricht", nachricht.getNachricht()));
	}

}
