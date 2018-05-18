package de.dhpoly.ressource.view;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class RessourcenDatensatzUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public RessourcenDatensatzUI(SpielfeldAnsicht ansicht)
	{
		super(ansicht);
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
