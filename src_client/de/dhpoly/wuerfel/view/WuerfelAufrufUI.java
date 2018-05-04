package de.dhpoly.wuerfel.view;

import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielfeldAnsicht;

public class WuerfelAufrufUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public WuerfelAufrufUI(SpielfeldAnsicht ansicht)
	{
		super(ansicht);
	}

	@Override
	public boolean isEinmalig()
	{
		return false;
	}

}
