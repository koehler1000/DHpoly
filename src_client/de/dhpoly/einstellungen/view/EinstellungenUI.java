package de.dhpoly.einstellungen.view;

import java.util.ArrayList;
import java.util.List;

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
	public void zeige(String beschreibung)
	{
		zeigeMitte(beschreibung);
	}

	@Override
	public List<Oberflaeche> durchHinzufuegenUngueltigWerdend(List<Oberflaeche> oberflaechen)
	{
		List<Oberflaeche> ret = new ArrayList<>();
		oberflaechen.stream().filter(e -> (e instanceof EinstellungenUI)).forEach((Oberflaeche e) -> ret.add(e));
		return ret;
	}
}
