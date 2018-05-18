package de.dhpoly.einstellungen.view;

import de.dhpoly.datenobjekt.Datenobjekt;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class EinstellungenUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public EinstellungenUI(SpielfeldAnsicht ansicht)
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
		zeigeMitte(beschreibung, objekt);
	}
}
