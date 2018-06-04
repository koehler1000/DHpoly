package de.dhpoly.feld.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.LineBorder;

import de.dhpoly.bilderverwalter.Bilderverwalter;
import de.dhpoly.feld.model.EreignisfeldDaten;
import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielUI;

public class EreignisfeldUI extends FeldUI // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public EreignisfeldUI(EreignisfeldDaten feld, SpielUI ansicht)
	{
		super(feld, ansicht);
		this.setBorder(new LineBorder(Color.BLACK));
		this.setBackground(Color.WHITE);

		this.add(ElementFactory.getBild(Bilderverwalter.EREIGNISFELD, Color.WHITE));
	}

	@Override
	public void zeige(String beschreibung)
	{
		zeigeLinks(beschreibung);
	}

	@Override
	public List<Oberflaeche> durchHinzufuegenUngueltigWerdend(List<Oberflaeche> oberflaechen)
	{
		return new ArrayList<>();
	}
}
