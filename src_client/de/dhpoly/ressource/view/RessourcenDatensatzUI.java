package de.dhpoly.ressource.view;

import java.util.ArrayList;
import java.util.List;

import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielUI;

public class RessourcenDatensatzUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public RessourcenDatensatzUI(SpielUI ansicht)
	{
		super(ansicht);
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
