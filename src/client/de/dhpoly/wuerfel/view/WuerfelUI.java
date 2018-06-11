package de.dhpoly.wuerfel.view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielUI;
import de.dhpoly.wuerfel.model.Wuerfel;

public class WuerfelUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public WuerfelUI(SpielUI ansicht, Wuerfel w)
	{
		super(ansicht);

		this.add(ElementFactory.getBild(w.getZahl()));
		this.setMaximumSize(new Dimension(200, 200));
		this.setPreferredSize(new Dimension(200, 200));

		this.remove(getSchliessenButton());
	}

	@Override
	public void zeige(String beschreibung)
	{
		zeigeLinks(beschreibung);
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return true;
	}

	@Override
	public List<Oberflaeche> durchHinzufuegenUngueltigWerdend(List<Oberflaeche> oberflaechen)
	{
		return new ArrayList<>();
	}
}