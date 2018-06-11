package de.dhpoly.wuerfel.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import de.dhpoly.oberflaeche.ElementFactory;
import de.dhpoly.oberflaeche.view.Oberflaeche;
import de.dhpoly.oberflaeche.view.SpielUI;
import de.dhpoly.wuerfel.model.Wuerfel;
import de.dhpoly.wuerfel.model.WuerfelDaten;

public class WuerfelDatenUI extends Oberflaeche // NOSONAR
{
	private static final long serialVersionUID = 1L;

	public WuerfelDatenUI(WuerfelDaten wuerfelDaten, SpielUI ansicht)
	{
		super(ansicht, 3);

		JPanel pnlInhalt = ElementFactory.erzeugePanel();
		pnlInhalt.setLayout(new GridLayout(1, wuerfelDaten.getWuerfel().size(), 10, 10));

		List<WuerfelUI> wuerfel = new ArrayList<>();

		for (Wuerfel w : wuerfelDaten.getWuerfel())
		{
			wuerfel.add(new WuerfelUI(ansicht, w));
		}

		wuerfel.forEach(pnlInhalt::add);

		JPanel pnl = ElementFactory.erzeugePanel();
		pnl.setLayout(new FlowLayout());
		pnl.add(pnlInhalt);
		this.add(pnl, BorderLayout.CENTER);
	}

	@Override
	public boolean isInvalideBeiSpielerWechsel()
	{
		return true;
	}

	@Override
	public void zeige(String beschreibung)
	{
		zeigeSpielfeldMitte(beschreibung);
	}

	@Override
	public List<Oberflaeche> durchHinzufuegenUngueltigWerdend(List<Oberflaeche> oberflaechen)
	{
		List<Oberflaeche> ret = new ArrayList<>();
		oberflaechen.stream().filter(e -> (e instanceof WuerfelDatenUI)).forEach(ret::add);
		return ret;
	}
}
